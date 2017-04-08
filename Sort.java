package helloworld;

public class Sort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {3,2,5,8,9,0,4,7};
		//insertSort(array);
	    //selectSort(array);
		//shellSort(array);
		//heapSort(array);
		//mergeSort(array); 
		//bubbleSort(array);
		// quickSort(array);
		countingSort(array);
		for(int e : array){
			System.out.println(" "+e);
		}
	}
	
	public static void  swap(int[] array,int i,int j) {
		  int temp = array[i];
		  array[i] = array[j];
		  array[j] = temp;
	}
	
	//ѡ������ÿ��һ��ѡ��һ����С������
	public static void selectSort(int[] array){
		int i = 0;
		int j = 0;
		int temp = 0;
		int minIndex = 0;  //��С����������
		
		for(i = 0 ;i < array.length - 1;i++){
			minIndex = i;
			for(j = i+1;j < array.length;j++){
				if(array[minIndex] > array[j]){
					minIndex = j;
				}
			}
			if(minIndex != i){
				temp = array[i];
				array[i] = array[minIndex];
				array[minIndex] = temp;
			}
		}
	}
	//  ��ʵ��������ȫ������
	//	�ѷ�Ϊ�󶥶Ѻ�С���ѣ�����Key[i]>=Key[2i+1]&&key>=key[2i+2]��Ϊ�󶥶ѣ�
	//	���� Key[i]<=key[2i+1]&&Key[i]<=key[2i+2]��ΪС���ѡ����������ʿ�֪��
	//	���ѵĶѶ��Ĺؼ��ֿ϶������йؼ��������ģ�С���ѵĶѶ��Ĺؼ��������йؼ�������С�ġ�
	//	������
	public static void heapSort(int[] array){
		int len = array.length - 1;
		buildHeap(array,len);  //����һ���󶥶�
		
		//�ѽ�������
//		������Ĺ��̾��ǣ������Ƴ���㣨����ĵ�һ����Ԫ�صĹ��̡� 
//		1�����Ƴ��Ķ���Ԫ�����������һ��Ԫ�ؽ���λ�á� 
//		2��ͬʱ�����ĳ��ȼ� 1�� 
//		3��Ȼ����µ���ʣ������ݣ�ʹ����϶ѵ����ԡ��� 
//		��ʱֻ��Ҫ�������Ԫ�ؼ��ɣ������㶼���Ѿ�����ʼ���õģ� 
//		�ظ��ù��̣�ֱ����Ҫ���������鳤��Ϊ 0 �� 
		for(int i = len;i>0;i--){
			swap(array,0,i);
			maxHeapify(array,0,i-1);
		}
		
	}
	 // len����������������ĸ���
	public static void maxHeapify(int[] array,int index,int len){ 
		int lchild = (index*2)+1;
		int rchild = lchild +1;
		int maxIndex = lchild;
		if(lchild > len){
			return;
		}
		if(rchild<=len && array[lchild] < array[rchild]){
			maxIndex = rchild;
		}
		if(array[maxIndex] > array[index] ){
			swap(array,maxIndex,index);
			maxHeapify(array,maxIndex,len);  //���ӽڵ㱻�滻������Ҫ��������
		}
	}
	public static void buildHeap(int[] array,int len){
		//	���һ����Ҷ�ڵ㿪ʼ����
		int beginIndex = (len-1)/2 ;
		for(int i = beginIndex;i>=0;i--){
			maxHeapify(array,i,len);
		}
	}
	
	
	//��������:  ����δ�������ݣ��������������дӺ���ǰɨ�裬�ҵ���Ӧλ�ò�����
	public static void insertSort(int[] array){
		int i = 0;
		int j = 0;
		int key = 0;
		
		for(i = 1; i < array.length; i++){
			key = array[i];
			j = i - 1;
			while(j >=0 && key < array[j]){
				array[j+1] = array[j];
				j-- ;
			}
			array[j+1] = key;
		}		
	}
	//ϣ������ͨ�����Ƚϵ�ȫ��Ԫ�ط�Ϊ��������������������������ܡ�����������һ��Ԫ�ؿ���һ���Եس�����λ��ǰ��һ��
	//����Ȼ���㷨��ȡԽ��ԽС�Ĳ������������㷨�����һ��������ͨ�Ĳ������򣬵��ǵ����ⲽ������������ݼ���������
	//�õ��ˣ���ʱ��������Ͽ죩��
	public static void shellSort(int[] array){
		int gap;
		int i,j;
		int key;
		//�����ı����2.2��ͳ�Ƹ��ʵıȽϺõĳ���������һ��֮�����б�Ҫ��ô������Ϊ�˷�ֹgapΪ1��
		for(gap = array.length/2;gap>0;gap= (int) (gap==2 ? 1 : gap/2.2f)){
			for(i=gap;i<array.length;i+=gap){
				key = array[i];
				j = i-gap;
				while(j>=0 && key<array[j]){
					array[j+gap] = array[j];
					j -= gap;
				}
				array[j+gap] = key;
			}
		}
	}
	
	//�鲢����:�����Ѿ���������кϲ���һ�����еĲ������ϲ��ķ���Ϊ����һ��ͬ��������ʱ�ռ䣬
	//�ӷֱ��ͷ��ʼ�Ƚ��������е�ֵ�����ѽ�С�����������Ӧ����ʱ�ռ���
	public static void mergeSort(int[] array){
		int[] tempArray = new int[array.length]; 
		mergeSort(array,tempArray,0,array.length-1);
	}
	public static void mergeSort(int[] array,int[] tempArray,int start,int end){
		if(start<end){
	        int mid=(start+end)/2;  //�����ص�
	        mergeSort(array,tempArray,start,mid);  //�ݹ���ã�����ǰ���array[start...mid]
	        mergeSort(array,tempArray,mid+1,end);  //�ݹ���ã��������array[mid+1...end]
	        merge(array,tempArray,start,mid+1,end);  //�鲢���������������顣
	    }
	}
	public static void merge(int[]array, int[] tempArray,int leftStart,
			int rightStart,int rightEnd){
		int leftEnd = rightStart - 1;
		int leftPos = leftStart;
		
		int rightPos = rightStart;
		
		int tempArrayPos = 0;
		
		//main loop
		while( (leftPos <= leftEnd) && (rightPos <= rightEnd) ){
			if(array[leftPos] <= array[rightPos] ){
				tempArray[tempArrayPos++] = array[leftPos++];
			}else{
				tempArray[tempArrayPos++] = array[rightPos++];
			}
		}		
		//move the left(ʣ���) to tempArray
		while(leftPos <= leftEnd){
			tempArray[tempArrayPos++] = array[leftPos++];
		}
		while(rightPos <= rightEnd){
			tempArray[tempArrayPos++] = array[rightPos++];
		}
		//ע��̬�ƶ�ÿ������кϲ���������㲻һ��Ϊ0
		for(int i = 0;i<tempArrayPos;i++){
			array[leftStart+i] = tempArray[i]; 
		}
	}
	
	//ð������
	public static void bubbleSort(int[] array){
		int i = 0;
		int j = 0;
		int temp = 0;
		for(i = 0;i<array.length;i++){
			for(j=0;j<array.length-i-1;j++){
				if(array[j]>array[j+1]){
					temp = array[j];
					array[j] = array[j+1];
					array[j+1] = temp ;
				}
			}
		}
	}
	
	//��������:��֧��ǳ���Ҫ����������ȡ���У�����֧���뵹���ڶ�λ�ô���������֤���һ��λ�õ�Ԫ�ش���֧��
	public static void quickSort(int[] array){
		quickSort(array,0,array.length-1);
	}
	public static void quickSort(int[] array,int low,int high){
		//sort the low,high,middle to find the pivot;
		int middle;
		int temp;
		middle = (low+high)/2;
		if(array[low]>array[middle]){
			temp = array[low];
			array[low] = array[middle];
			array[middle] = temp;
		}
		if(array[low]>array[high]){
			temp = array[low];
			array[low] = array[high];
			array[high] = temp;
		}
		if(array[middle]>array[high]){
			temp = array[middle];
			array[middle] = array[high];
			array[high] = temp;
		}
		
		if(high<=2){
			return;
		}
		
		temp = array[middle];
		array[middle] = array[high-1];
		array[high-1] = temp;
		int pivot = array[high-1];
		
		//Begin partitioning
		int i;
		int j;
		for(i=low,j=high-1;;){
			while(array[i]<=pivot && i<j){
				i++;
			}
			while(array[j]>=pivot && i<j){
				j--;
			}
			if(i>=j){
				break;
			}
			temp = array[i];
			array[i] = array[j];
			array[j] = temp;
		}
		
		temp = array[i];
		array[i] = array[high-1];
		array[high-1] = temp;
		
		if(i-1<0 || i+1>high){
			System.out.println("I:"+i);
			return;
		}
		quickSort(array,low,i-1);
		quickSort(array,i+1,high);
	}	
	
	//��������,ֻ����0��n��Χ������
	public static void countingSort(int[] array){
		int max = 0;
		for(int e:array){
			if(max<e){
				max = e;
			}
		}
		
		int countingArray[] = new int[max+1];
		
		for(int i=0; i <countingArray.length;i++){  //initialize array
			countingArray[i] = 0;
		}
		for(int i=0;i<array.length;i++){
			countingArray[array[i]]++;   //counting 
		}

		for(int i=1;i<countingArray.length;i++){
			countingArray[i] +=countingArray[i-1];
		}

		
		int sortedArray[] = new int[array.length];
		for(int i=sortedArray.length-1;i>=0;i--){
			int e = array[i];
			int index = countingArray[e];
			sortedArray[index-1] = e;	
			countingArray[e]--;
		}
//		
//		
//		array = sortedArray;
		for(int i=0;i<array.length;i++){
			array[i] = sortedArray[i];			
		}
//		array = sortedArray;  //���鸳ֵ��ֻ�������ڱ�����
//		array = sortedArray.clone();  // ��ֵ��ֻ�������ڱ�����
//		for(int i=0;i<array.length;i++){
//			System.out.println(array[i]);			
//		}
						
		//return array;
	}
}
