package top_interview_question;

public class Problem_0033_SearchInRotatedSortedArray {
    public int search(int[] arr, int target) {
        int l = 0;
        int m = 0;
        int r = arr.length - 1;
        while (l <= r) {
            m = (l + r) >> 1;
            if (arr[m] == target) {
                return m;
            }

            if (arr[l] == arr[m] && arr[m] == arr[r]) {
                while (l != m && arr[l] == arr[m]) {
                    l++;
                }

                if (l == m) {
                    l = m + 1;
                    continue;
                }
            }

            if (arr[l] != arr[m]) {
                if (arr[m] > arr[l]) {
                    if (target >= arr[l] && target < arr[m]) {
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                } else {
                    if (target > arr[m] && target <= arr[r]) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                }
            } else {
                if (arr[m] < arr[r]) {
                    if (target >= arr[m] && target < arr[r]) {
                        l = m + 1;
                    } else {
                        r = m - 1;
                    }
                } else {
                    if (target >= arr[l] && target < arr[m]) {
                        r = m - 1;
                    } else {
                        l = m + 1;
                    }
                }
            }
        }
        return -1;
    }
}
