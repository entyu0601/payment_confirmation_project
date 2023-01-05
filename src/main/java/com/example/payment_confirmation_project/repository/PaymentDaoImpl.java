package com.example.payment_confirmation_project.repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.payment_confirmation_project.service.impl.BaseDao;
import com.example.payment_confirmation_project.vo.PaymentInfo;

public class PaymentDaoImpl extends BaseDao {

	/* doQuery　With　Limit　And　PageSize --> 検索数（検索機能） */
	public List<PaymentInfo> doQueryWithPageSizeAndStartPosition(int pageSize, int startPosition) {
		StringBuffer sb = new StringBuffer(); 
		sb.append(" select new com.example.payment_confirmation_project.vo.PaymentInfo"
				+ "(pay.id, pay.objectId, per.building, per.ownerName, per.tenantName, per.rent, pay.paymentDeadline,"
				+ " pay.paymentDate, pay.paymentMethod, pay.lateChecked, pay.paymentMonths, pay.paymentAmount, pay.rentsMonth) "
				+ "	from Personal per join Payment pay on per.objectId = pay.objectId");
		Map<String, Object> params = new HashMap<>();
		return doQuery(sb.toString(), params, PaymentInfo.class, pageSize, startPosition);
	}

}
