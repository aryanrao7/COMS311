package homework;

public class HW3 {

    //Part B- 1.
    public int dcInversions(int[] a) {
        if (a == null || a.length <= 1) {
            return 0; // No inversions in an empty array
        }
        
        int[] temp = new int[a.length];
        return mergeSortAndCountInversions(a, temp, 0, a.length - 1);
    }

    private int mergeSortAndCountInversions(int[] a, int[] temp, int left, int right) {
        int inversions = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;

            inversions += mergeSortAndCountInversions(a, temp, left, mid); // Count inversions in left subarray
            inversions += mergeSortAndCountInversions(a, temp, mid + 1, right); // Count inversions in right subarray
            inversions += mergeAndCountSplitInversions(a, temp, left, mid, right); // Count inversions across the split
        }
        return inversions;
    }

    private int mergeAndCountSplitInversions(int[] a, int[] temp, int left, int mid, int right) {
        for (int i = left; i <= right; i++) {
            temp[i] = a[i];
        }

        int inversions = 0;
        int i = left;
        int j = mid + 1;
        int k = left;

        while (i <= mid && j <= right) {
            if (temp[i] <= temp[j]) {
                a[k++] = temp[i++];
            } else {
                a[k++] = temp[j++];
                inversions += mid - i + 1; // Count inversions
            }
        }

        while (i <= mid) {
            a[k++] = temp[i++];
        }

        return inversions;
    }

    //Part B- 2.

    public int maxSum(int[] a) {
        if (a == null || a.length == 0) {
            return 0;
        }

        return maxSumRecursive(a, 0, a.length - 1);
    }

    private int maxSumRecursive(int[] a, int left, int right) {
        if (left == right) {
            return a[left];
        }

        int mid = left + (right - left) / 2;

        int maxLeftSum = maxSumRecursive(a, left, mid);
        int maxRightSum = maxSumRecursive(a, mid + 1, right);
        int maxCrossingSum = maxCrossingSum(a, left, mid, right);

        return Math.max(Math.max(maxLeftSum, maxRightSum), maxCrossingSum);
    }

    private int maxCrossingSum(int[] a, int left, int mid, int right) {
        int leftSum = Integer.MIN_VALUE;
        int sum = 0;
        for (int i = mid; i >= left; i--) {
            sum += a[i];
            if (sum > leftSum) {
                leftSum = sum;
            }
        }

        int rightSum = Integer.MIN_VALUE;
        sum = 0;
        for (int i = mid + 1; i <= right; i++) {
            sum += a[i];
            if (sum > rightSum) {
                rightSum = sum;
            }
        }

        return leftSum + rightSum;
    }
}