/*
 * Copyright 2018，广州信天翁信息科技有限公司 
 */
package org.interview;

/**
 * <p>标题： 根据一个数字打印出矩形数字
 * <p>功能： TODO
 * <p>创建日期：2018年1月19日 下午6:34:13
 * <p>{@link org.interview.ArrayAlgorithm}
 * @author qpyong
 */
public class ArrayAlgorithm {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int[][] array = getArray(6);
		printlnArray(array);
	}
	
	/**
	 * @param array
	 */
	private static void printlnArray(int[][] array) {
		for (int[] arr : array) {
			for (int item : arr) {
				System.out.print(item < 10 ? "0" + item : item);
				System.out.print(" ");
			}
			System.out.println();
		}
	}
	
	/**
	 * 对于参数<code>num</code>，计算出二维<code>num</code>元数组。假设<code>num</code>=4，则有如下二维数组：
	 * <table>
	 * <tr><td>01</td><td>12</td><td>11</td><td><td>10</td></tr>
	 * <tr><td>02</td><td>13</td><td>16</td><td><td>09</td></tr>
	 * <tr><td>03</td><td>14</td><td>15</td><td><td>08</td></tr>
	 * <tr><td>04</td><td>05</td><td>06</td><td><td>07</td></tr>
	 * </table>
	 * 计算时，按照左->下->右->上的顺序递增计算，每计算完这4个方向的值，算做一轮。对于每一轮：
	 * <ol>
	 * <li>从左侧即第0列开始逐行向下递增；
	 * <li>从下方即第num-1行开始逐列向右递增；
	 * <li>从右方即第num-1列开始逐行向上递增；
	 * <li>从上方即第0行开始逐列向左递增。
	 * </ol>
	 * 对于当前元素array[i][j]，如果已计算过（值>0）则忽略不做计算；否则将上一个单元的值递增作为本单元的值。
	 * @param num
	 * @return
	 */
	private static int[][] getArray(int num) {
		if (num <= 1)
			return null;
		int[][] array = new int[num][num];
		int left = 0;// 列号，从左到右；对同一列，从上到下计算
		int down = num - 1;// 行号，从下到上；对于同一行，从左到右计算
		int right = num - 1;// 列号，从右到左；对同一列，从下到上计算
		int up = 0;// 行号，从上到下；对同一行，从右到左
		int round = (int) Math.ceil(num / 2);// 终点
		int index = 0;
		while (index++ <= round) {
			// 左
			for (int row = 0; row < num; row++) {
				int value = array[row][left];
				if (value > 0)
					continue;
				array[row][left] = array[row == 0 ? 0 : row - 1][left] + 1;
			}
			left++;
			// 下
			for (int column = 0; column < num; column++) {
				int value = array[down][column];
				if (value > 0)
					continue;
				array[down][column] = array[down][column == 0 ? 0 : column - 1] + 1;
			}
			down--;
			// 右
			for (int row = num - 1; row >= 0; row--) {
				int value = array[row][right];
				if (value > 0)
					continue;
				array[row][right] = array[row + 1][right] + 1;
			}
			right--;
			// 上
			for (int column = num - 1; column >= 0; column--) {
				int value = array[up][column];
				if (value > 0)
					continue;
				array[up][column] = array[up][column + 1] + 1;
			}
			up++;
		}
		return array;
	}
}
