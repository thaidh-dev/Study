using System;
using System.Collections;
using System.Data;
using OutSystems.HubEdition.RuntimePlatform;
using OutSystems.RuntimePublic.Db;

namespace OutSystems.NssExtension {

	public class CssExtension: IssExtension {

		/// <summary>
		/// 
		/// </summary>
		/// <param name="ssPrice"></param>
		/// <param name="ssQuantity"></param>
		/// <param name="ssTotal"></param>
		public void MssCalculate(decimal ssPrice, int ssQuantity, out decimal ssTotal) {
			ssTotal = 0.0M;
			// TODO: Write implementation for action
		} // MssCalculate

	} // CssExtension

} // OutSystems.NssExtension

