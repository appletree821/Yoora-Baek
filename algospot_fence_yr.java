import java.util.Scanner;

public class algospot_fence_yr {
	//울타리 배열을 받아올 변수 설정
	private int[] fence;
	//fence변수에 받아온 배열을 algospot_fence_yr 클래스의 객체변수로 설정
	public algospot_fence_yr(int[] fence) {
		this.fence = fence;
	}
	
	public int solve() {
		//head와 tail의 인덱스 값을 넘김
		return solve(0, fence.length - 1);
	}
	
	private int solve(int head, int tail) {
		//판자가 한개인 경우
		if(head == tail)
			return fence[head];
		//판자가 두개 이상인 경우 분할정복 방식을 통해 max값을 계속 update한다 
		int middle = (head + tail) / 2;
		int max = Math.max(solve(head, tail), solve(middle + 1, tail));
		
		//쪼개서 얻은 결과와 중간 펜스를 기준으로 구한 max와 다시 비교
		max = Math.max(getMaxArea(head, tail, middle), max);
		
		return max;
	}
	
	private int getMaxArea(int head, int tail, int select) {
		
		int minHeight = Math.min(fence[select], fence[select + 1]);
		int max = minHeight * 2;
		int left = select - 1;
		int right = select + 2;
		
		//select 기준점으로부터 좌우로 높이가 큰 펜스부터 하나씩 찾아가야함
		while(head <= left || right <= tail) {
			int selectedHeight = 987654321;
			//오른쪽으로 더 직사각형 확장 가능한 경우
			if(left < head && right <= tail) {
				selectedHeight = fence[right];
				right++;
			}
			//왼쪽으로 직사각형을 더 확장 가능한 경우
			else if(head <= left && tail < right) {
				selectedHeight = fence[left];
				left--;
			}
			else {
				//큰쪽부터 골라서 비
				if(fence[left] > fence[right]) {
					selectedHeight = fence[left];
					left--;
				}
				else {
					selectedHeight = fence[right];
					right++;
				}
			}
			minHeight = Math.min(minHeight, selectedHeight);
			max = Math.max(max,  (right - left - 1)*minHeight);
		}
		
		return max;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Scanner sc = new Scanner(System.in);
		
		int testCase = sc.nextInt();
		for(int i=0;i<testCase;i++) {
			//판자 수 
			int boardCount = sc.nextInt();
			//판자수 크기의 배열 fence 할당
			int[] fence = new int[boardCount];
			// 판자 크기만큼 판자의 크기 각각 해당 인덱스에 입력 
			for(int j = 0;j < boardCount;j++) {
				fence[j] = sc.nextInt();
			}
			//객체 생성 
			algospot_fence_yr main = new algospot_fence_yr(fence);
			System.out.println(main.solve());
		}
		sc.close();
	}

}
