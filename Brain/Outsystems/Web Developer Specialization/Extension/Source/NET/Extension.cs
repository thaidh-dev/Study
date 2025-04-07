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
		public void MssCalculate(decimal ssPrice, int ssQuantity, out decimal ssTotal) {
			// TODO: Write implementation for action
			ssTotal = ssQuantity * ssPrice;
        } // MssCalculate

	} // CssExtension

} // OutSystems.NssExtension

