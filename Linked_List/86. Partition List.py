# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, val=0, next=None):
        self.val = val
        self.next = next
class Solution(object):
    def partition(self, head, x):
        """
        :type head: Optional[ListNode]
        :type x: int
        :rtype: Optional[ListNode]
        """
        if not head or not head.next:
            return head

        small_dummy = ListNode(0)
        big_dummy = ListNode(0)

        small_tail = small_dummy
        big_tail = big_dummy

        current = head

        while current:
            if current.val < x:
                small_tail.next = current
                small_tail = small_tail.next
            else:
                big_tail.next = current
                big_tail = big_tail.next

            current = current.next

        small_tail.next = big_dummy.next
        big_tail.next = None

        return small_dummy.next



