import torch
import torch.nn as nn
import torch.optim as optim
import torch.nn.functional as F
from torchvision import datasets, transforms
from torch.utils.data import DataLoader
import matplotlib.pyplot as plt
import numpy as np
import time

# 设置随机种子以保证结果可重现
torch.manual_seed(42)
if torch.cuda.is_available():
    
    torch.cuda.manual_seed(42)

# 检查CUDA是否可用
device = torch.device("cuda" if torch.cuda.is_available() else "cpu")
print(f"使用设备: {device}")
if torch.cuda.is_available():
    print(f"GPU型号: {torch.cuda.get_device_name(0)}")
    print(f"CUDA版本: {torch.version.cuda}")


class MNIST_CNN(nn.Module):
    def __init__(self):
        super(MNIST_CNN, self).__init__()
        # 第一个卷积层: 1个输入通道 -> 32个输出通道
        self.conv1 = nn.Conv2d(1, 32, kernel_size=3, padding=1)
        # 第二个卷积层: 32个输入通道 -> 64个输出通道
        self.conv2 = nn.Conv2d(32, 64, kernel_size=3, padding=1)
        # 最大池化层
        self.pool = nn.MaxPool2d(2, 2)
        # Dropout层防止过拟合
        self.dropout1 = nn.Dropout(0.25)
        self.dropout2 = nn.Dropout(0.5)
        # 全连接层
        self.fc1 = nn.Linear(64 * 7 * 7, 128)
        self.fc2 = nn.Linear(128, 10)

    def forward(self, x):
        # 第一个卷积块: Conv -> ReLU -> Pool -> Dropout
        x = self.pool(F.relu(self.conv1(x)))
        x = self.dropout1(x)

        # 第二个卷积块: Conv -> ReLU -> Pool -> Dropout
        x = self.pool(F.relu(self.conv2(x)))
        x = self.dropout1(x)

        # 展平特征图
        x = x.view(-1, 64 * 7 * 7)

        # 全连接层
        x = F.relu(self.fc1(x))
        x = self.dropout2(x)
        x = self.fc2(x)

        return F.log_softmax(x, dim=1)


def load_data():
    """加载和预处理数据"""
    transform = transforms.Compose([
        transforms.ToTensor(),
        transforms.Normalize((0.1307,), (0.3081,))
    ])

    # 下载并加载数据集
    train_dataset = datasets.MNIST(
        root='./data',
        train=True,
        download=True,
        transform=transform
    )

    test_dataset = datasets.MNIST(
        root='./data',
        train=False,
        download=True,
        transform=transform
    )

    # 创建数据加载器
    batch_size = 128  # 在GPU上可以使用更大的batch_size
    train_loader = DataLoader(train_dataset, batch_size=batch_size, shuffle=True, num_workers=2)
    test_loader = DataLoader(test_dataset, batch_size=batch_size, shuffle=False, num_workers=2)

    print(f"训练集大小: {len(train_dataset)}")
    print(f"测试集大小: {len(test_dataset)}")
    print(f"批次大小: {batch_size}")

    return train_loader, test_loader


def visualize_samples(train_loader):
    """可视化一些训练样本"""
    data_iter = iter(train_loader)
    images, labels = next(data_iter)

    # 将数据移回CPU进行可视化
    images = images.cpu()
    labels = labels.cpu()

    fig, axes = plt.subplots(2, 5, figsize=(12, 5))
    for i in range(10):
        ax = axes[i // 5, i % 5]
        img = images[i][0] * 0.3081 + 0.1307  # 反归一化
        ax.imshow(img.numpy(), cmap='gray')
        ax.set_title(f'Label: {labels[i].item()}')
        ax.axis('off')
    plt.tight_layout()
    plt.show()


def train(model, device, train_loader, optimizer, epoch):
    """训练函数"""
    model.train()
    train_loss = 0
    correct = 0
    total = 0
    batch_time = 0

    start_time = time.time()

    for batch_idx, (data, target) in enumerate(train_loader):
        # 将数据移动到GPU
        data, target = data.to(device), target.to(device)

        optimizer.zero_grad()
        output = model(data)
        loss = F.nll_loss(output, target)
        loss.backward()
        optimizer.step()

        train_loss += loss.item()
        _, predicted = output.max(1)
        total += target.size(0)
        correct += predicted.eq(target).sum().item()

        # 每100个batch打印一次进度
        if batch_idx % 100 == 0:
            batch_time = time.time() - start_time
            print(f'Epoch: {epoch} [{batch_idx * len(data)}/{len(train_loader.dataset)} '
                  f'({100. * batch_idx / len(train_loader):.0f}%)]\t'
                  f'Loss: {loss.item():.6f}\tTime: {batch_time:.2f}s')

    avg_loss = train_loss / len(train_loader)
    accuracy = 100. * correct / total
    epoch_time = time.time() - start_time

    print(f'训练完成 - Epoch: {epoch}\t平均损失: {avg_loss:.4f}\t'
          f'准确率: {accuracy:.2f}%\t时间: {epoch_time:.2f}s')

    return avg_loss, accuracy, epoch_time


def test(model, device, test_loader):
    """测试函数"""
    model.eval()
    test_loss = 0
    correct = 0
    total = 0

    start_time = time.time()

    with torch.no_grad():
        for data, target in test_loader:
            data, target = data.to(device), target.to(device)
            output = model(data)
            test_loss += F.nll_loss(output, target, reduction='sum').item()
            pred = output.argmax(dim=1, keepdim=True)
            correct += pred.eq(target.view_as(pred)).sum().item()
            total += target.size(0)

    test_loss /= total
    accuracy = 100. * correct / total
    test_time = time.time() - start_time

    print(f'测试结果 - 平均损失: {test_loss:.4f}\t'
          f'准确率: {correct}/{total} ({accuracy:.2f}%)\t'
          f'时间: {test_time:.2f}s')

    return test_loss, accuracy, test_time


def main():
    """主函数"""
    print("=== MNIST手写数字识别 (CUDA版本) ===\n")

    # 加载数据
    print("1. 加载数据...")
    train_loader, test_loader = load_data()

    # 可视化样本
    print("\n2. 可视化训练样本...")
    visualize_samples(train_loader)

    # 创建模型
    print("\n3. 创建模型...")
    model = MNIST_CNN().to(device)
    print(model)

    # 打印模型参数数量
    total_params = sum(p.numel() for p in model.parameters())
    trainable_params = sum(p.numel() for p in model.parameters() if p.requires_grad)
    print(f"\n模型总参数: {total_params:,}")
    print(f"可训练参数: {trainable_params:,}")

    # 定义优化器和学习率调度器
    optimizer = optim.Adam(model.parameters(), lr=0.001, weight_decay=1e-4)
    scheduler = optim.lr_scheduler.StepLR(optimizer, step_size=5, gamma=0.7)

    # 存储训练历史
    train_losses = []
    train_accuracies = []
    test_losses = []
    test_accuracies = []
    train_times = []
    test_times = []

    # 训练循环
    epochs = 10
    print(f"\n4. 开始训练 ({epochs}个epochs)...")

    total_start_time = time.time()

    for epoch in range(1, epochs + 1):
        print(f"\n=== 第 {epoch}/{epochs} 轮训练 ===")

        # 训练
        train_loss, train_acc, train_time = train(model, device, train_loader, optimizer, epoch)
        train_losses.append(train_loss)
        train_accuracies.append(train_acc)
        train_times.append(train_time)

        # 测试
        test_loss, test_acc, test_time = test(model, device, test_loader)
        test_losses.append(test_loss)
        test_accuracies.append(test_acc)
        test_times.append(test_time)

        # 更新学习率
        scheduler.step()
        current_lr = optimizer.param_groups[0]['lr']
        print(f"当前学习率: {current_lr}")

    total_time = time.time() - total_start_time
    print(f"\n=== 训练完成 ===")
    print(f"总训练时间: {total_time:.2f}s ({total_time / 60:.2f}分钟)")
    print(f"平均每个epoch时间: {np.mean(train_times):.2f}s")

    # 保存模型
    print("\n5. 保存模型...")
    torch.save({
        'model_state_dict': model.state_dict(),
        'optimizer_state_dict': optimizer.state_dict(),
        'train_losses': train_losses,
        'test_losses': test_losses,
        'train_accuracies': train_accuracies,
        'test_accuracies': test_accuracies,
        'epochs': epochs
    }, 'mnist_cnn_model_cuda.pth')
    print("模型已保存为 'mnist_cnn_model_cuda.pth'")

    # 绘制结果
    print("\n6. 绘制训练结果...")
    plot_results(train_losses, test_losses, train_accuracies, test_accuracies, train_times)

    # 显示错误分类的样本
    print("\n7. 分析错误分类...")
    show_misclassified(model, device, test_loader)

    # 性能测试
    print("\n8. 性能测试...")
    performance_test(model, device, test_loader)


def plot_results(train_losses, test_losses, train_accuracies, test_accuracies, train_times):
    """绘制训练结果"""
    epochs = range(1, len(train_losses) + 1)

    plt.figure(figsize=(15, 5))

    # 损失曲线
    plt.subplot(1, 3, 1)
    plt.plot(epochs, train_losses, 'b-', label='训练损失', linewidth=2)
    plt.plot(epochs, test_losses, 'r-', label='测试损失', linewidth=2)
    plt.xlabel('Epoch')
    plt.ylabel('Loss')
    plt.title('训练和测试损失')
    plt.legend()
    plt.grid(True, alpha=0.3)

    # 准确率曲线
    plt.subplot(1, 3, 2)
    plt.plot(epochs, train_accuracies, 'b-', label='训练准确率', linewidth=2)
    plt.plot(epochs, test_accuracies, 'r-', label='测试准确率', linewidth=2)
    plt.xlabel('Epoch')
    plt.ylabel('Accuracy (%)')
    plt.title('训练和测试准确率')
    plt.legend()
    plt.grid(True, alpha=0.3)

    # 训练时间
    plt.subplot(1, 3, 3)
    plt.plot(epochs, train_times, 'g-', label='训练时间', linewidth=2)
    plt.xlabel('Epoch')
    plt.ylabel('Time (s)')
    plt.title('每个Epoch的训练时间')
    plt.legend()
    plt.grid(True, alpha=0.3)

    plt.tight_layout()
    plt.show()

    # 打印最终结果
    print(f"\n最终结果:")
    print(f"训练准确率: {train_accuracies[-1]:.2f}%")
    print(f"测试准确率: {test_accuracies[-1]:.2f}%")
    print(f"最佳测试准确率: {max(test_accuracies):.2f}%")


def show_misclassified(model, device, test_loader):
    """显示错误分类的样本"""
    model.eval()
    misclassified = []

    with torch.no_grad():
        for data, target in test_loader:
            data, target = data.to(device), target.to(device)
            output = model(data)
            pred = output.argmax(dim=1)

            # 找出错误分类的样本
            mask = pred != target
            misclassified_data = data[mask]
            misclassified_pred = pred[mask]
            misclassified_target = target[mask]

            for i in range(min(10, len(misclassified_data))):
                if len(misclassified) >= 10:
                    break
                misclassified.append((
                    misclassified_data[i].cpu(),
                    misclassified_pred[i].cpu(),
                    misclassified_target[i].cpu()
                ))

    # 显示错误分类的样本
    if misclassified:
        fig, axes = plt.subplots(2, 5, figsize=(12, 5))
        for i, (img, pred, target) in enumerate(misclassified[:10]):
            ax = axes[i // 5, i % 5]
            img_display = img[0] * 0.3081 + 0.1307  # 反归一化
            ax.imshow(img_display.numpy(), cmap='gray')
            ax.set_title(f'预测: {pred.item()}, 真实: {target.item()}', color='red')
            ax.axis('off')
        plt.suptitle('错误分类的样本', fontsize=16)
        plt.tight_layout()
        plt.show()
    else:
        print("没有找到错误分类的样本！")


def performance_test(model, device, test_loader):
    """性能测试"""
    print("进行性能测试...")
    model.eval()

    # 测试推理速度
    start_time = time.time()
    correct = 0
    total = 0

    with torch.no_grad():
        for data, target in test_loader:
            data, target = data.to(device), target.to(device)
            output = model(data)
            pred = output.argmax(dim=1)
            correct += pred.eq(target).sum().item()
            total += target.size(0)

    inference_time = time.time() - start_time
    accuracy = 100. * correct / total

    print(f"推理性能:")
    print(f"总测试样本: {total}")
    print(f"总推理时间: {inference_time:.2f}s")
    print(f"平均每张图片推理时间: {inference_time * 1000 / total:.2f}ms")
    print(f"准确率: {accuracy:.2f}%")
    print(f"吞吐量: {total / inference_time:.2f} 样本/秒")

    # GPU内存使用情况
    if torch.cuda.is_available():
        gpu_memory = torch.cuda.max_memory_allocated() / 1024 ** 3  # 转换为GB
        print(f"GPU内存使用: {gpu_memory:.2f} GB")


def load_and_test_model():
    """加载保存的模型并进行测试"""
    print("\n=== 加载保存的模型进行测试 ===")

    # 创建新模型实例
    loaded_model = MNIST_CNN().to(device)

    # 加载保存的权重
    checkpoint = torch.load('mnist_cnn_model_cuda.pth')
    loaded_model.load_state_dict(checkpoint['model_state_dict'])
    loaded_model.eval()

    print("模型加载成功！")

    # 进行测试
    train_loader, test_loader = load_data()
    test_loss, test_acc, test_time = test(loaded_model, device, test_loader)

    return loaded_model


if __name__ == "__main__":
    # 运行主程序
    main()

    # 可选：加载保存的模型进行测试
    # loaded_model = load_and_test_model()