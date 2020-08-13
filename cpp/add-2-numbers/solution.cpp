#include <iostream>

// A very rudimentary linked list.
struct ListNode {
    int val;
    ListNode *next;
    ListNode() : val(0), next(nullptr) {}
    ListNode(int n) : val(n), next(nullptr) {}
    ListNode(int n, ListNode *next) : val(n), next(next) {}
};

class Solution {
public:
    ListNode* addTwoNumbers(ListNode* l1, ListNode* l2) {
        int carry = 0;
        ListNode* lp1 = l1; // Sliding pointer to l1.
        ListNode* lp2 = l2; // Sliding pointer to l2.
        ListNode* lp3;
        ListNode* l3;

        l3 = new ListNode(0);
        lp3 = l3;
        bool firstNode = true;

        while (lp1 || lp2) {
            int m = lp1 ? lp1->val : 0;
            int n = lp2 ? lp2->val : 0;
            int r = (m + n + carry) % 10; // remainder
            int q = (m + n + carry) / 10; // quotient
            carry = (m + n + carry) > 9 ? q : 0;

            if (firstNode) {
                lp3->val = r;
            } else {
                lp3->next = new ListNode(r);
            }

            lp1 = lp1 ? lp1->next : lp1;
            lp2 = lp2 ? lp2->next : lp2;

            if (firstNode) {
                firstNode = false;
            } else {
                lp3 = lp3->next;
            }
        }

        if (carry != 0)
            lp3->next = new ListNode(carry);

        return l3;        
    }
};

ListNode* buildList(int n) 
{
    if (n == 1) {
        ListNode *l1 = new ListNode(2);
        ListNode *l2 = new ListNode(4);
        ListNode *l3 = new ListNode(3);

        l1->next = l2;
        l2->next = l3;

        return l1;
    }  else {
        ListNode *l1 = new ListNode(5);
        ListNode *l2 = new ListNode(6);
        ListNode *l3 = new ListNode(4);

        l1->next = l2;
        l2->next = l3;

        return l1;
    }
}

void show(ListNode *l)
{
    ListNode *lp = l;
    bool first = true;

    while (lp != nullptr) {
        if (first) {
            first = false;
            std::cout << lp->val;
        } else {
            std::cout << "->" << lp->val;
        }
        lp = lp->next;
    }
    std::cout << std::endl;
}

int main(int argc, char* argv[], char *envp[])
{
    Solution s;
    ListNode *l1 = buildList(1);
    show(l1);
    ListNode *l2 = buildList(2);
    show(l2);
    ListNode *sum = s.addTwoNumbers(l1, l2);
    show(sum);

    return 0;
}

