/**
 * 
 */
package com.flatironschool.javacs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;


/**
 * Provides sorting algorithms.
 *
 */
public class ListSorter<T> {

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void insertionSort(List<T> list, Comparator<T> comparator) {

		for (int i = 1; i < list.size(); i++) {
			T elt_i = list.get(i);
			int j = i;
			while (j > 0) {
				T elt_j = list.get(j - 1);
				if (comparator.compare(elt_i, elt_j) >= 0) {
					break;
				}
				list.set(j, elt_j);
				j--;
			}
			list.set(j, elt_i);
		}
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void mergeSortInPlace(List<T> list, Comparator<T> comparator) {
		List<T> sorted = mergeSort(list, comparator);
		list.clear();
		list.addAll(sorted);
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * Returns a list that might be new.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public List<T> mergeSort(List<T> list, Comparator<T> comparator) {
		//FILL THIS IN
		List<T> current = new ArrayList<>();
		current.addAll(list);
		int n = current.size();
		if(n <= 1){
			return current;
		}
		
		List<T> beg = new ArrayList();
		List<T> end = new ArrayList();
		
		
		
		beg = current.subList(0, n/2);
		end = current.subList(n/2, n);
		
		
		
		List<T> new_beg = mergeSort(beg, comparator);
		List<T> new_end = mergeSort(end, comparator);
		
		
		List<T> new_List = merge(new_beg, new_end, comparator);
	
		return new_List;
		
	}

	public List<T> merge(List<T> beg, List<T> end, Comparator<T> comparator) {
		
		int left_ind = 0;
		int right_ind = 0;
		int left_end = beg.size() - 1;
		int right_end = end.size() - 1;
		
		List<T> result = new ArrayList();
		
		while(left_ind <= left_end && right_ind <= right_end){
			if(comparator.compare(beg.get(left_ind), end.get(right_ind)) <= 0) {
				result.add(beg.get(left_ind));
				left_ind ++;
			}
			else{
				result.add(end.get(right_ind));
				right_ind++;
			}
		}
		
		while(left_ind <= left_end){
			result.add(beg.get(left_ind));
			left_ind ++;
		}
		
		while(right_ind <= right_end){
			result.add(end.get(right_ind));
			right_ind ++;
		}
		
		return result;
	}

	/**
	 * Sorts a list using a Comparator object.
	 * 
	 * @param list
	 * @param comparator
	 * @return
	 */
	public void heapSort(List<T> list, Comparator<T> comparator) {
		// FILL THIS IN!
		PriorityQueue<T> heap = new PriorityQueue<>(list.size());
		
		for(T item: list){
		heap.offer(item);
	}
		list.clear();
		while(heap.size() != 0){
		list.add(heap.poll());
		}
		System.out.println(list);
	}

	/**
	 * Returns the largest `k` elements in `list` in ascending order.
	 * 
	 * @param k
	 * @param list
	 * @param comparator
	 * @return
	 * @return
	 */
	public List<T> topK(int k, List<T> list, Comparator<T> comparator) {
		// FILL THIS IN!
		PriorityQueue<T> heap = new PriorityQueue<>(k);
		List<T> result = new ArrayList<>();
		
		for (T item : list){
			
			if(heap.size() < k){
				
				heap.offer(item);
			}
			else{
			if(comparator.compare(item, heap.peek()) > 0){
					heap.poll();
					heap.offer(item);
				}
			
			if(comparator.compare(item, heap.peek()) < 0){
				continue;
			}
			}
		}
		
		
		while(heap.size() != 0){
			result.add(heap.poll());
			}
	
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));

		Comparator<Integer> comparator = new Comparator<Integer>() {
			@Override
			public int compare(Integer n, Integer m) {
				return n.compareTo(m);
			}
		};

		ListSorter<Integer> sorter = new ListSorter<Integer>();
		sorter.insertionSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.mergeSortInPlace(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(3, 5, 1, 4, 2));
		sorter.heapSort(list, comparator);
		System.out.println(list);

		list = new ArrayList<Integer>(Arrays.asList(20, 3, 5, 8, 1, 4, 12, 7));
		List<Integer> queue = sorter.topK(4, list, comparator);
		System.out.println(queue);
	}
}
