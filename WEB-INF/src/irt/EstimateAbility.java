package irt;

import java.util.ArrayList;

public class EstimateAbility {

	//一番目が能力値で、二番目が事後標準偏差
	public ArrayList<Double> estimateTheta(ArrayList<Integer> u, ArrayList<Double> a, ArrayList<Double> b) {

		// 求積点
		double[] Xm = { -4.15989, -3.92869, -3.69862, -3.46959, -3.24151, -3.01432, -2.78794, -2.5623, -2.33732,
				-2.11295, -1.88912, -1.66576, -1.44283, -1.22025, -0.997977, -0.775951, -0.554115, -0.332415,
				-0.110796,
				0.110796, 0.332415, 0.554115, 0.775951, 0.997977, 1.22025, 1.44283, 1.66576, 1.88912, 2.11295,
				2.33732,
				2.5623, 2.78794, 3.01432, 3.24151, 3.46959, 3.69862, 3.92869, 4.15989 };

		// 総和=1となるように基準化された重み
		double[] Wm = { 7.08E-9, 4.57E-8, 2.63E-7, 1.35E-6, 6.22E-6, 2.57E-5, 9.52E-5, 3.17E-4, 9.53E-4, 0.00257927,
				0.006303, 0.0139157, 0.027779, 0.0501758, 0.082052, 0.121538, 0.16313, 0.19846, 0.21889, 0.21889,
				0.19846, 0.16313, 0.121538, 0.082052, 0.0501758, 0.027779, 0.0139157, 0.006303, 0.00257927, 9.53E-4,
				3.17E-4, 9.52E-5, 2.57E-5, 6.22E-6, 1.35E-6, 2.63E-7, 4.57E-8, 7.08E-9 };

		// 尤度関数を求めるために用いる変数
		double result; // 結果を求める用(Rによる項目反応理論P.171でいうLm[m])
		double logit; // expの中身
		double ICC; // 1.0 / (1.0 + Math.exp(logit))
		int i, j; // ループカウンター

		double Numerator[] = new double[Xm.length];// ベイズの分子
		double Denominator[] = new double[Xm.length]; // ベイズの分母

		double Dsum = 0.0; // 分母の和
		double eapTheta = 0.0; // 能力値
		double gm = 0.0; // 事後分布の重み
		double sd = 0.0; // 事後標準偏差

		// 尤度関数を求める
		for (i = 0; i < Xm.length; i++) {
			result = 1;
			for (j = 0; j < u.size(); j++) {
				switch (u.get(j)) {
				case 1:
					logit = -1.7 * a.get(j) * (Xm[i] - b.get(j));
					ICC = 1.0 / (1.0 + Math.exp(logit));
					result *= ICC;
					break;
				case 0:
					logit = -1.7 * a.get(j) * (Xm[i] - b.get(j));
					ICC = 1.0 / (1.0 + Math.exp(logit));
					result *= (1 - ICC);
					break;
				}
			}

			Numerator[i] = result * Xm[i] * Wm[i];
			Denominator[i] = result * Wm[i];

		}

		for (int h = 0; h < Xm.length; h++) {
			Dsum += Denominator[h];
		}

		for (int x = 0; x < Xm.length; x++) {
			eapTheta += Numerator[x] / Dsum;
		}

		//事後分布の重みgmを求める
		for (int l = 0; l < Wm.length; l++) {
			gm += (Numerator[l] / Xm[l]) / Dsum;
		}

		for (int z = 0; z < Xm.length; z++) {
			sd += Math.pow((Xm[z] - eapTheta), 2) * gm;
		}
		sd = Math.sqrt(sd);

		System.out.println("推定された能力値：" + eapTheta + "//推定されたSD" + sd);

		//戻り値の宣言
		ArrayList<Double> returnList = new ArrayList<Double>();
		returnList.add(eapTheta);
		returnList.add(sd);
		return returnList;
	}
}