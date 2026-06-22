# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution(object):
    def deleteDuplicates(self, head):
        """
        :type head: Optional[ListNode]
        :rtype: Optional[ListNode]
        """
        current = head
        if not head or not head.next:
            return head
        while current:
            if current.next and current.val == current.next.val:
                while current.next and current.val == current.next.val:
                    current.next = current.next.next
            else:
                current = current.next

        return head