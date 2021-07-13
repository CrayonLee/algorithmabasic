package top_interview_question;

public class Problem_0041_FirstMissingPositive {
    public int firstMissingPositive(int[] arr) {
        int l = 0;
        int r = arr.length;

        while (l < r) {
            if (arr[l] == l + 1) {
                l++;
            } else if (arr[l] <= l || arr[l] > r || arr[l] == arr[arr[l] - 1]) {
                swap(arr, l, --r);
            } else {
                //这种可能性的意思是  本来不应该出现在l位置上的数出现在了l位置上 但是应该出现该值的位置上却没有出现
                swap(arr, l, arr[l] - 1);
            }
        }
        return l+1;
    }

    public void swap(int[] arr, int i, int j) {
        int tmp = arr[i];
        arr[i] = arr[j];
        arr[j] = tmp;
    }
}
