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
	
	//选择排序每走一趟选择一个最小数索引
	public static void selectSort(int[] array){
		int i = 0;
		int j = 0;
		int temp = 0;
		int minIndex = 0;  //最小的数的坐标
		
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
	//  堆实际上是完全二叉树
	//	堆分为大顶堆和小顶堆，满足Key[i]>=Key[2i+1]&&key>=key[2i+2]称为大顶堆，
	//	满足 Key[i]<=key[2i+1]&&Key[i]<=key[2i+2]称为小顶堆。由上述性质可知大
	//	顶堆的堆顶的关键字肯定是所有关键字中最大的，小顶堆的堆顶的关键字是所有关键字中最小的。
	//	堆排序
	public static void heapSort(int[] array){
		int len = array.length - 1;
		buildHeap(array,len);  //建成一个大顶堆
		
		//堆进行排序
//		堆排序的过程就是，不断移出最顶层（数组的第一个）元素的过程。 
//		1、把移出的顶层元素与数组最后一个元素交换位置。 
//		2、同时遍历的长度减 1， 
//		3、然后从新调整剩余的数据，使其符合堆的特性。（ 
//		此时只需要调整最顶层元素即可，其它层都是已经被初始化好的） 
//		重复该过程，直至需要遍历的数组长度为 0 。 
		for(int i = len;i>0;i--){
			swap(array,0,i);
			maxHeapify(array,0,i-1);
		}
		
	}
	 // len在这里代表的是数组的个数
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
			maxHeapify(array,maxIndex,len);  //若子节点被替换，则需要继续调整
		}
	}
	public static void buildHeap(int[] array,int len){
		//	最后一个非叶节点开始调整
		int beginIndex = (len-1)/2 ;
		for(int i = beginIndex;i>=0;i--){
			maxHeapify(array,i,len);
		}
	}
	
	
	//插入排序:  对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
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
	//希尔排序通过将比较的全部元素分为几个区域来提升插入排序的性能。这样可以让一个元素可以一次性地朝最终位置前进一大
	//步。然后算法再取越来越小的步长进行排序，算法的最后一步就是普通的插入排序，但是到了这步，需排序的数据几乎是已排
	//好的了（此时插入排序较快）。
	public static void shellSort(int[] array){
		int gap;
		int i,j;
		int key;
		//除数改变成了2.2是统计概率的比较好的除数，后面一步之所以有必要这么复杂是为了防止gap为1；
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
	
	//归并排序:两个已经排序的序列合并成一个序列的操作，合并的方法为创建一个同样长度临时空间，
	//从分别从头开始比较两个序列的值，并把较小的数放入相对应的临时空间中
	public static void mergeSort(int[] array){
		int[] tempArray = new int[array.length]; 
		mergeSort(array,tempArray,0,array.length-1);
	}
	public static void mergeSort(int[] array,int[] tempArray,int start,int end){
		if(start<end){
	        int mid=(start+end)/2;  //数组重点
	        mergeSort(array,tempArray,start,mid);  //递归调用，排序前半段array[start...mid]
	        mergeSort(array,tempArray,mid+1,end);  //递归调用，排序后半段array[mid+1...end]
	        merge(array,tempArray,start,mid+1,end);  //归并上述两段有序数组。
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
		//move the left(剩余的) to tempArray
		while(leftPos <= leftEnd){
			tempArray[tempArrayPos++] = array[leftPos++];
		}
		while(rightPos <= rightEnd){
			tempArray[tempArrayPos++] = array[rightPos++];
		}
		//注意态势对每个组进行合并，所以起点不一定为0
		for(int i = 0;i<tempArrayPos;i++){
			array[leftStart+i] = tempArray[i]; 
		}
	}
	
	//冒泡排序
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
	
	//快速排序:找支点非常重要，可以三者取其中，并把支点与倒数第二位置处交换，保证最后一个位置的元素大于支点
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
	
	//计数排序,只能排0到n范围的整数
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
//		array = sortedArray;  //数组赋值后，只能作用于本函数
//		array = sortedArray.clone();  // 赋值后，只能作用于本函数
//		for(int i=0;i<array.length;i++){
//			System.out.println(array[i]);			
//		}
						
		//return array;
	}
}
