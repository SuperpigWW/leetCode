# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution(object):
    def removeNthFromEnd(self, head, n):
        """
        :type head: Optional[ListNode]
        :type n: int
        :rtype: Optional[ListNode]
        """
        num = 0
        current = head
        while current:
            num += 1
            current = current.next
        remove = num - n + 1
        if num == n:
            return head.next # if delete head
        new = head
        numremove = 0
        while new:
            if numremove == remove - 2:
                new.next = new.next.next
            else:
                new = new.next
            numremove = numremove + 1

        return head