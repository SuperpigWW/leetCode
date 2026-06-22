# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution(object):
    def reorderList(self, head):
        """
        :type head: Optional[ListNode]
        :rtype: None Do not return anything, modify head in-place instead.
        """
        if not head:
            return head

        list1 = []
        current = head
        while current:
            list1.append(current)
            current = current.next

        count = len(list1)
        i, j = 0, count - 1
        while i < j:
            list1[i].next = list1[j]
            i += 1
            if i == j:
                break
            list1[j].next = list1[i]
            j -= 1

        list1[i].next = None



