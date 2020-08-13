#include <stdio.h>
#include <assert.h>

double findMedianSortedArrays(int *a1, int len1, int *a2, int len2)
{
    assert(len1 >= 0);
    assert(len2 >= 0);
    assert(a1 != NULL);
    assert(a2 != NULL);
    /* Median position of combined array. */
    int mpos = (len1 + len2) <= 0 ? 0 : (len1 + len2)/2 + 1;
    /* Position counter. */
    int pc = 0;
    /* Two median positions. */
    int med = 0;
    int med1 = 0;
    /* Two indices. */
    int i = 0;
    int j = 0;

    while (pc++ < mpos) {
        med1 = med;

        if (i >= len1) {
            med = a2[j++];
        } else if (j >= len2) {
            med = a1[i++];
        } else {
            med = (a1[i] < a2[j]) ? a1[i++] : a2[j++];
        }
    }

    return ((len1 + len2) % 2) == 0 ? (med + med1)/2.0 : med;
}

int main(int argc, char *argv[], char *envp[])
{
	int a1[] = {1, 2};
	int a2[] = {3, 4};
	int a3[] = {1, 3};
	int a4[] = {2};

	int m = findMedianSortedArrays(a1, 2, a2, 2);
	printf("Median = %d\n", m);

	m = findMedianSortedArrays(a3, 1, a4, 1);
	printf("Median = %d\n", m);

	return 0;
}

