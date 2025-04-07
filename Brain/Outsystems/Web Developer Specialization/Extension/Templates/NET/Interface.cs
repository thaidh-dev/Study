using System;
using System.Collections;
using System.Data;
using OutSystems.HubEdition.RuntimePlatform;

namespace OutSystems.NssExtension {

	public interface IssExtension {

		/// <summary>
		/// 
		/// </summary>
		/// <param name="ssPrice"></param>
		/// <param name="ssQuantity"></param>
		/// <param name="ssTotal"></param>
		void MssCalculate(decimal ssPrice, int ssQuantity, out decimal ssTotal);

	} // IssExtension

} // OutSystems.NssExtension
