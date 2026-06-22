# Definition for singly-linked list.
class ListNode(object):
    def __init__(self, x):
        self.val = x
        self.next = None

class Solution(object):
    def getIntersectionNode(self, headA, headB):
        """
        :type head1, head1: ListNode
        :rtype: ListNode
        """
        if not headA or not headB:
            return None

        currA = headA
        currB = headB

        while currA and currB:
            currA = currA.next
            currB = currB.next

        currA = headA
        currB = headB
        lenA = 0
        lenB = 0

        while currA:
            lenA += 1
            currA = currA.next

        while currB:
            lenB += 1
            currB = currB.next

        currA = headA
        currB = headB

        if lenA > lenB:
            diff = lenA - lenB
            for _ in range(diff):
                currA = currA.next
        elif lenB > lenA:
            diff = lenB - lenA
            for _ in range(diff):
                currB = currB.next

        while currA and currB:
            if currA == currB:
                return currA
            currA = currA.next
            currB = currB.next

        return None


