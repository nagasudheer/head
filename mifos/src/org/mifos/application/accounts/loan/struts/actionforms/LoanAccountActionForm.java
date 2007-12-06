/**

 * LoanAccountActionForm.java    version: 1.0

 

 * Copyright (c) 2005-2006 Grameen Foundation USA

 * 1029 Vermont Avenue, NW, Suite 400, Washington DC 20005

 * All rights reserved.

 

 * Apache License 
 * Copyright (c) 2005-2006 Grameen Foundation USA 
 * 

 * Licensed under the Apache License, Version 2.0 (the "License"); you may
 * not use this file except in compliance with the License. You may obtain
 * a copy of the License at http://www.apache.org/licenses/LICENSE-2.0 
 *

 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and limitations under the 

 * License. 
 * 
 * See also http://www.apache.org/licenses/LICENSE-2.0.html for an explanation of the license 

 * and how it is applied.  

 *

 */
package org.mifos.application.accounts.loan.struts.actionforms;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.action.ActionMessage;
import org.mifos.application.accounts.loan.struts.uihelpers.PaymentDataHtmlBean;
import org.mifos.application.accounts.loan.util.helpers.LoanAccountDetailsViewHelper;
import org.mifos.application.accounts.loan.util.helpers.LoanConstants;
import org.mifos.application.accounts.loan.util.helpers.LoanExceptionConstants;
import org.mifos.application.accounts.loan.util.helpers.RepaymentScheduleInstallment;
import org.mifos.application.accounts.util.helpers.AccountState;
import org.mifos.application.accounts.util.helpers.PaymentDataTemplate;
import org.mifos.application.configuration.util.helpers.ConfigurationConstants;
import org.mifos.application.customer.business.CustomerBO;
import org.mifos.application.customer.business.service.CustomerBusinessService;
import org.mifos.application.customer.util.helpers.CustomerConstants;
import org.mifos.application.fees.business.FeeView;
import org.mifos.application.master.business.CustomFieldDefinitionEntity;
import org.mifos.application.master.business.CustomFieldView;
import org.mifos.application.meeting.exceptions.MeetingException;
import org.mifos.application.personnel.business.PersonnelBO;
import org.mifos.application.personnel.persistence.PersonnelPersistence;
import org.mifos.application.productdefinition.business.LoanOfferingBO;
import org.mifos.application.util.helpers.Methods;
import org.mifos.application.util.helpers.YesNoFlag;
import org.mifos.framework.business.service.ServiceFactory;
import org.mifos.framework.exceptions.ApplicationException;
import org.mifos.framework.exceptions.PageExpiredException;
import org.mifos.framework.exceptions.PersistenceException;
import org.mifos.framework.exceptions.PropertyNotFoundException;
import org.mifos.framework.exceptions.ServiceException;
import org.mifos.framework.security.util.UserContext;
import org.mifos.framework.struts.actionforms.BaseActionForm;
import org.mifos.framework.util.helpers.BusinessServiceName;
import org.mifos.framework.util.helpers.Constants;
import org.mifos.framework.util.helpers.DateUtils;
import org.mifos.framework.util.helpers.ExceptionConstants;
import org.mifos.framework.util.helpers.Money;
import org.mifos.framework.util.helpers.SessionUtils;
import org.mifos.framework.util.helpers.StringUtils;

public class LoanAccountActionForm extends BaseActionForm {

    public LoanAccountActionForm() {
		super();
		defaultFees = new ArrayList<FeeView>();
		additionalFees = new ArrayList<FeeView>();
		customFields = new ArrayList<CustomFieldView>();
		clients = new ArrayList<String>();
		clientDetails = new ArrayList<LoanAccountDetailsViewHelper>();
    }

//	For individual monitoring
    
    private List<String> clients; 
    
    private List<LoanAccountDetailsViewHelper> clientDetails;
    
    private String perspective;

	private String accountId;

	private String globalAccountNum;

	private String prdOfferingName;

	private String accountName;

	private String accountTypeId;

	private String customerId;

	private String prdOfferingId;

	private String loanAmount;

	private String interestRate;

	private String noOfInstallments;

	private String disbursementDate;

	private String intDedDisbursement;

	private String loanOfferingFund;

	private String gracePeriodDuration;

	private String businessActivityId;

	private String collateralTypeId;

	private String collateralNote;

	private List<FeeView> defaultFees;

	private List<FeeView> additionalFees;

	private String stateSelected;

	private String gracePeriod;

	private List<CustomFieldView> customFields;

    private List<PaymentDataHtmlBean> paymentDataBeans = new ArrayList();

    // For Repayment day
    
	private String monthRank;
	
	private String weekRank;
	
	private String monthWeek;
	
	private String weekDay;
	
	private String recurMonth;
		
	private String firstRepaymentDay;
	
    public String getMonthRank() {
		return monthRank;
	}

	public void setMonthRank(String monthRank) {
		this.monthRank = monthRank;
	}

	public String getMonthWeek() {
		return monthWeek;
	}

	public void setMonthWeek(String monthWeek) {
		this.monthWeek = monthWeek;
	}

	public String getRecurMonth() {
		return recurMonth;
	}

	public void setRecurMonth(String recurMonth) {
		this.recurMonth = recurMonth;
	}

	public String getWeekDay() {
		return weekDay;
	}

	public void setWeekDay(String weekDay) {
		this.weekDay = weekDay;
	}

	public String getWeekRank() {
		return weekRank;
	}

	public void setWeekRank(String weekRank) {
		this.weekRank = weekRank;
	}

	public List<PaymentDataHtmlBean> getPaymentDataBeans() {
        return this.paymentDataBeans;
    }

    public String getGracePeriod() {
		return gracePeriod;
	}

	public void setGracePeriod(String gracePeriod) {
		this.gracePeriod = gracePeriod;
	}

	public String getAccountId() {
		return accountId;
	}

	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public String getAccountTypeId() {
		return accountTypeId;
	}

	public void setAccountTypeId(String accountTypeId) {
		this.accountTypeId = accountTypeId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getGlobalAccountNum() {
		return globalAccountNum;
	}

	public void setGlobalAccountNum(String globalAccountNum) {
		this.globalAccountNum = globalAccountNum;
	}

	public String getPrdOfferingName() {
		return prdOfferingName;
	}

	public void setPrdOfferingName(String prdOfferingName) {
		this.prdOfferingName = prdOfferingName;
	}

	public String getPrdOfferingId() {
		return prdOfferingId;
	}

	public void setPrdOfferingId(String prdOfferingId) {
		this.prdOfferingId = prdOfferingId;
	}

	public String getBusinessActivityId() {
		return businessActivityId;
	}

	public void setBusinessActivityId(String businessActivityId) {
		this.businessActivityId = businessActivityId;
	}

	public String getCollateralNote() {
		return collateralNote;
	}

	public void setCollateralNote(String collateralNote) {
		this.collateralNote = collateralNote;
	}

	public String getCollateralTypeId() {
		return collateralTypeId;
	}

	public void setCollateralTypeId(String collateralTypeId) {
		this.collateralTypeId = collateralTypeId;
	}

	public String getDisbursementDate() {
		return disbursementDate;
	}

	public void setDisbursementDate(String disbursementDate) {
		this.disbursementDate = disbursementDate;
	}

	public String getGracePeriodDuration() {
		return gracePeriodDuration;
	}

	public void setGracePeriodDuration(String gracePeriodDuration) {
		this.gracePeriodDuration = gracePeriodDuration;
	}

	public String getIntDedDisbursement() {
		return intDedDisbursement;
	}

	public void setIntDedDisbursement(String intDedDisbursement) {
		this.intDedDisbursement = intDedDisbursement;
	}

	public String getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(String interestRate) {
		this.interestRate = interestRate;
	}

	public String getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(String loanAmount) {
		this.loanAmount = loanAmount;
	}

	public String getLoanOfferingFund() {
		return loanOfferingFund;
	}

	public void setLoanOfferingFund(String loanOfferingFund) {
		this.loanOfferingFund = loanOfferingFund;
	}

	public String getNoOfInstallments() {
		return noOfInstallments;
	}

	public void setNoOfInstallments(String noOfInstallments) {
		this.noOfInstallments = noOfInstallments;
	}

	
	public List<CustomFieldView> getCustomFields() {
		return customFields;
	}

	public void setCustomFields(List<CustomFieldView> customFields) {
		this.customFields = customFields;
	}

	public CustomFieldView getCustomField(int i){
		while(i>=customFields.size()){
			customFields.add(new CustomFieldView());
		}
		return customFields.get(i);
	}
	
	public List<FeeView> getAdditionalFees() {
		return additionalFees;
	}

	public void setAdditionalFees(List<FeeView> additionalFees) {
		this.additionalFees = additionalFees;
	}

	public List<FeeView> getDefaultFees() {
		return defaultFees;
	}

	public void setDefaultFees(List<FeeView> defaultFees) {
		this.defaultFees = defaultFees;
	}

	public String getStateSelected() {
		return stateSelected;
	}

	public void setStateSelected(String stateSelected) {
		this.stateSelected = stateSelected;
	}

	public AccountState getState() throws PropertyNotFoundException {
		return AccountState.fromShort(getShortValue(getStateSelected()));
	}

	public Integer getCustomerIdValue() {
		return getIntegerValue(getCustomerId());
	}

	public Short getPrdOfferingIdValue() {
		return getShortValue(getPrdOfferingId());
	}

	public Short getGracePeriodDurationValue() {
		return getShortValue(getGracePeriodDuration());
	}

	public Money loanAmountValue() {
		return getMoney(getLoanAmount());
	}

	public Short getNoOfInstallmentsValue() {
		return getShortValue(getNoOfInstallments());
	}

	public Date getDisbursementDateValue(Locale locale) {
		return DateUtils.getLocaleDate(locale, getDisbursementDate());
	}
	
	public Date getFirstRepaymentDayValue(Locale locale) {
		return DateUtils.getLocaleDate(locale, getFirstRepaymentDay());
	}
	
	
	public boolean isInterestDedAtDisbValue() {
		return getBooleanValue(getIntDedDisbursement());
	}

	public Double getInterestDoubleValue() {
		return getDoubleValue(getInterestRate());
	}

	public Short getLoanOfferingFundValue() {
		return getShortValue(getLoanOfferingFund());
	}

	public Integer getBusinessActivityIdValue() {
		return getIntegerValue(getBusinessActivityId());
	}

	public Short getCollateralTypeIdValue() {
		return getShortValue(getCollateralTypeId());
	}

	public Money getLoanAmountValue() {
		return getMoney(loanAmount);
	}

	public Double getInterestRateValue() {
		return getDoubleValue(interestRate);
	}

	public FeeView getDefaultFee(int i) {
		while (i >= defaultFees.size()) {
			defaultFees.add(new FeeView());
		}
		return defaultFees.get(i);
	}
	
	public Boolean isInterestDeductedAtDisbursment(){
		if(getIntDedDisbursement().equals("1"))
			return true;
		return false;
	}

    public void initializeTransactionFields(UserContext userContext, List<RepaymentScheduleInstallment> installments) {
        this.paymentDataBeans = new ArrayList(installments.size());
        PersonnelBO personnel;
        try {
            personnel = new PersonnelPersistence().getPersonnel(userContext.getId());
        } catch (PersistenceException e) {
            throw new IllegalArgumentException("bad UserContext id");
        }
        
        for (Iterator<RepaymentScheduleInstallment> iter = installments.iterator(); iter.hasNext();) {
            this.paymentDataBeans.add(new PaymentDataHtmlBean(
                    userContext.getPreferredLocale(), personnel, iter.next()));
        }
    }

    public List<FeeView> getFeesToApply() {
		List<FeeView> feesToApply = new ArrayList<FeeView>();
		for (FeeView fee : getAdditionalFees())
			if (fee.getFeeIdValue() != null)
				feesToApply.add(fee);
		for (FeeView fee : getDefaultFees())
			if (!fee.isRemoved())
				feesToApply.add(fee);
		return feesToApply;
	}

	public FeeView getSelectedFee(int index) {
		while (index >= additionalFees.size())
			additionalFees.add(new FeeView());
		return additionalFees.get(index);
	}

	@Override
	public void reset(ActionMapping mapping, HttpServletRequest request) {
		super.reset(mapping, request);
		String method = request.getParameter(Methods.method.toString());
		if (method.equals(Methods.schedulePreview.toString())) {
			intDedDisbursement = null;
			for (int i = 0; i < defaultFees.size(); i++) {
				// if an already checked fee is unchecked then the value set to
				// 0
				if (request.getParameter("defaultFee[" + i + "].feeRemoved") == null) {
					defaultFees.get(i).setFeeRemoved(YesNoFlag.NO.getValue());
				}
			}
		} else if (method.equals(Methods.managePreview.toString())) {
			intDedDisbursement = "0";
		}

	}

	@Override
	public ActionErrors validate(ActionMapping mapping,
			HttpServletRequest request) {
		String method = request.getParameter(Methods.method.toString());
		ActionErrors errors = new ActionErrors();
		if (null == request.getAttribute(Constants.CURRENTFLOWKEY))
			request.setAttribute(Constants.CURRENTFLOWKEY, request
					.getParameter(Constants.CURRENTFLOWKEY));
		try {
			if (method.equals(Methods.getPrdOfferings.toString()))
				checkValidationForGetPrdOfferings(errors);
			else if (method.equals(Methods.load.toString()))
				checkValidationForLoad(errors);
			else if (method.equals(Methods.schedulePreview.toString()))
				checkValidationForSchedulePreview(errors, request);
			else if (method.equals(Methods.managePreview.toString()))
				checkValidationForManagePreview(errors, request);
            else if (method.equals(Methods.preview.toString()))
                checkValidationForPreview(errors, request);
        } catch (ApplicationException ae) {
			// Discard other errors (is that right?)
        	ae.printStackTrace();
			errors = new ActionErrors();
			errors.add(ae.getKey(), new ActionMessage(ae.getKey(), ae
					.getValues()));
		}
		if (!errors.isEmpty()) {
			request.setAttribute("methodCalled", method);
		}
		return errors;
	}

	// TODO: use localized strings for error messages rather than hardcoded
	private void checkValidationForGetPrdOfferings(ActionErrors errors) {
		if (StringUtils.isNullOrEmpty(getCustomerId()))
			addError(errors, LoanConstants.CUSTOMER,
					LoanConstants.CUSTOMERNOTSELECTEDERROR,
					ConfigurationConstants.CLIENT, ConfigurationConstants.GROUP);
	}

	// TODO: use localized strings for error messages rather than hardcoded
	private void checkValidationForLoad(ActionErrors errors) {
		checkValidationForGetPrdOfferings(errors);
		if (StringUtils.isNullOrEmpty(getPrdOfferingId()))
			addError(errors, LoanConstants.PRDOFFERINGID,
					LoanConstants.LOANOFFERINGNOTSELECTEDERROR,
					ConfigurationConstants.LOAN, LoanConstants.INSTANCENAME);
	}

	private void checkValidationForSchedulePreview(ActionErrors errors,
			HttpServletRequest request) throws ApplicationException {
		checkValidationForPreviewBefore(errors, request);
		validateFees(request, errors);
		validateCustomFields(request, errors);
		validateIndividualLoanFields(request, errors);
		validateNumberOfSelectedMembers(request, errors);
		validateSumOfTheAmountsSpecified(request, errors);
		validateRepaymentDayRequired(request, errors);
	}

    private void checkValidationForPreview(ActionErrors errors,
			HttpServletRequest request) throws ApplicationException {
        validateRedoLoanPayments(request, errors);
	}

    private void checkValidationForManagePreview(ActionErrors errors,
			HttpServletRequest request) throws ApplicationException {
		if(getState().equals(AccountState.LOAN_PARTIAL_APPLICATION) || getState().equals(AccountState.LOAN_PENDING_APPROVAL))
			checkValidationForPreview(errors, request);
		validateCustomFields(request, errors);
	}
	
	private void checkValidationForPreviewBefore(ActionErrors errors,
			HttpServletRequest request) throws ApplicationException {
		LoanOfferingBO loanOffering = (LoanOfferingBO) SessionUtils
				.getAttribute(LoanConstants.LOANOFFERING, request);
		checkForMinMax(errors, getLoanAmount(), loanOffering.getMaxLoanAmount()
				.getAmountDoubleValue(), loanOffering.getMinLoanAmount()
				.getAmountDoubleValue(), LoanConstants.LOANAMOUNT);
		checkForMinMax(errors, getInterestRate(), loanOffering
				.getMaxInterestRate(), loanOffering.getMinInterestRate(),
				LoanConstants.INTERESTRATE);
		checkForMinMax(errors, getNoOfInstallments(), loanOffering
				.getMaxNoInstallments(), loanOffering.getMinNoInstallments(),
				LoanConstants.NO_OF_INST);
		if (StringUtils.isNullOrEmpty(getDisbursementDate())) {
			addError(errors, "Proposed/Actual disbursal date",
					"errors.validandmandatory", "disbursal date");
		}
		if(isInterestDedAtDisbValue()){
			setGracePeriodDuration("0");
		}
		if (((!isInterestDedAtDisbValue()) && StringUtils
				.isNullOrEmpty(getGracePeriodDuration()))
				|| (getDoubleValue(getGracePeriodDuration()) != null
						&& getDoubleValue(getNoOfInstallments()) != null && getDoubleValue(getGracePeriodDuration()) >= getDoubleValue(getNoOfInstallments()))) {
			String noInst = StringUtils.isNullOrEmpty(getNoOfInstallments()) ? getStringValue(loanOffering
					.getMaxNoInstallments())
					: getNoOfInstallments();
			addError(errors, LoanConstants.GRACEPERIODDURATION,
					LoanConstants.GRACEPERIODERROR,
					LoanConstants.GRACEPERIODDURATION, noInst);
		}

	}

	
	private void checkForMinMax(ActionErrors errors, String currentValue,
			Short maxValue, Short minValue, String field) {
		if (StringUtils.isNullOrEmpty(currentValue)
				|| getDoubleValue(currentValue).doubleValue() > maxValue
						.doubleValue()
				|| getDoubleValue(currentValue).doubleValue() < minValue
						.doubleValue()) {
			addError(errors, field, LoanExceptionConstants.INVALIDMINMAX,
					field, getStringValue(minValue), getStringValue(maxValue));
		}
	}

	private void checkForMinMax(ActionErrors errors, String currentValue,
			Double maxValue, Double minValue, String field) {
		if (StringUtils.isNullOrEmpty(currentValue)
				|| getDoubleValue(currentValue).doubleValue() > maxValue
						.doubleValue()
				|| getDoubleValue(currentValue).doubleValue() < minValue
						.doubleValue()) {
			addError(errors, field, LoanExceptionConstants.INVALIDMINMAX,
					field, getStringValue(minValue), getStringValue(maxValue));
		}
	}

	protected void validateFees(HttpServletRequest request, ActionErrors errors)
			throws ApplicationException {
		validateForFeeAmount(errors);
		validateForDuplicatePeriodicFee(request, errors);
	}

	protected void validateForFeeAmount(ActionErrors errors) {
		List<FeeView> feeList = getFeesToApply();
		for (FeeView fee : feeList) {
			if (StringUtils.isNullOrEmpty(fee.getAmount()))
				errors.add(LoanConstants.FEE, new ActionMessage(
						LoanConstants.ERRORS_SPECIFY_FEE_AMOUNT));
		}
	}

	protected void validateForDuplicatePeriodicFee(HttpServletRequest request,
			ActionErrors errors) throws ApplicationException {
		List<FeeView> additionalFeeList = (List<FeeView>) SessionUtils
				.getAttribute(LoanConstants.ADDITIONAL_FEES_LIST, request);
		for (FeeView selectedFee : getAdditionalFees()) {
			int count = 0;
			for (FeeView duplicateSelectedfee : getAdditionalFees()) {
				if (selectedFee.getFeeIdValue() != null
						&& selectedFee.getFeeId().equals(
								duplicateSelectedfee.getFeeId())) {
					if (isSelectedFeePeriodic(selectedFee, additionalFeeList))
						count++;
				}
			}
			if (count > 1) {
				errors.add(LoanConstants.FEE, new ActionMessage(
						LoanConstants.ERRORS_DUPLICATE_PERIODIC_FEE));
				break;
			}
		}
	}

	private boolean isSelectedFeePeriodic(FeeView selectedFee,
			List<FeeView> additionalFeeList) {
		for (FeeView fee : additionalFeeList)
			if (fee.getFeeId().equals(selectedFee.getFeeId()))
				return fee.isPeriodic();
		return false;
	}
	
	private  void validateCustomFields(HttpServletRequest request, ActionErrors errors) {
		try {
			List<CustomFieldDefinitionEntity> customFieldDefs =(List<CustomFieldDefinitionEntity>) SessionUtils.getAttribute(CustomerConstants.CUSTOM_FIELDS_LIST, request);
			for(CustomFieldView customField : customFields){
				boolean isErrorFound = false;
				for(CustomFieldDefinitionEntity customFieldDef : customFieldDefs){
					if(customField.getFieldId().equals(customFieldDef.getFieldId())&& customFieldDef.isMandatory()){
						if(StringUtils.isNullOrEmpty(customField.getFieldValue())){
							errors.add(LoanConstants.CUSTOM_FIELDS, new ActionMessage(LoanConstants.ERRORS_SPECIFY_CUSTOM_FIELD_VALUE));
							isErrorFound = true;
							break;
						}
					}
				}
				if(isErrorFound)
					break;
			}
		} catch (PageExpiredException pee) {
			errors.add(ExceptionConstants.PAGEEXPIREDEXCEPTION,
					new ActionMessage(ExceptionConstants.PAGEEXPIREDEXCEPTION));
		}
	}
	
	private  void validateNumberOfSelectedMembers(HttpServletRequest request, ActionErrors errors) {
		try {
			CustomerBO customer = getCustomer(request);
		    Integer loanIndividualMonitoringIsEnabled = (Integer) SessionUtils.getAttribute(
	        		LoanConstants.LOANINDIVIDUALMONITORINGENABLED, request);      
			if (null != loanIndividualMonitoringIsEnabled
					&& 0 != loanIndividualMonitoringIsEnabled.intValue()
					&& customer.getCustomerLevel().isGroup()) {
				List<String> ids_clients_selected = getClients();
				List<String> selected_clients = new ArrayList();
				for (String id : ids_clients_selected) {
					if (null != id && !"".equals(id)) {
						selected_clients.add(id);

					}
				}
				if (selected_clients.size() < 2) {
					addError(errors,"",LoanExceptionConstants.NUMBEROFSELECTEDMEMBERSISNOTATLEASTTWO,"");
				}


			}
		}
		catch (PageExpiredException pee) {
			errors.add(ExceptionConstants.PAGEEXPIREDEXCEPTION,
					new ActionMessage(ExceptionConstants.PAGEEXPIREDEXCEPTION));
		}
		catch (ServiceException e) {
			errors.add(ExceptionConstants.FRAMEWORKRUNTIMEEXCEPTION,
					new ActionMessage(
							ExceptionConstants.FRAMEWORKRUNTIMEEXCEPTION));
		}
	}

	private  void validateSumOfTheAmountsSpecified(HttpServletRequest request, ActionErrors errors) {
		try {
			CustomerBO customer = getCustomer(request);
		    Integer loanIndividualMonitoringIsEnabled = (Integer) SessionUtils.getAttribute(
	        		LoanConstants.LOANINDIVIDUALMONITORINGENABLED, request);            
            
			if (null != loanIndividualMonitoringIsEnabled
					&& 0 != loanIndividualMonitoringIsEnabled.intValue()
					&& customer.getCustomerLevel().isGroup()) {

				List<String> ids_clients_selected = getClients();
				List<LoanAccountDetailsViewHelper> listdetail = getClientDetails();
			
				double totalAmout=new Double(0);
				for (LoanAccountDetailsViewHelper tempAccount : listdetail) {
					if (ids_clients_selected.contains(tempAccount
							.getClientId())) {
						if (tempAccount.getLoanAmount() != null){
							totalAmout = totalAmout
									+ tempAccount.getLoanAmount().doubleValue();							
						}
					}
				
				}
				LoanOfferingBO loanOffering = (LoanOfferingBO) SessionUtils
				.getAttribute(LoanConstants.LOANOFFERING, request);
				if (StringUtils.isNullOrEmpty(Double.valueOf(totalAmout).toString())
					   	|| getDoubleValue(Double.valueOf(totalAmout).toString()).doubleValue() > loanOffering.getMaxLoanAmount()
								.getAmountDoubleValue()
						|| getDoubleValue(Double.valueOf(totalAmout).toString()).doubleValue() < loanOffering.getMinLoanAmount()
								.getAmountDoubleValue()) {
					addError(errors, LoanConstants.LOANAMOUNT, LoanExceptionConstants.SUMOFINDIVIDUALAMOUNTSISNOTINTHERANGEOFALLOWEDAMOUNTS, getStringValue(loanOffering.getMinLoanAmount()
									.getAmountDoubleValue()), getStringValue(loanOffering.getMaxLoanAmount()
											.getAmountDoubleValue()));
					
				}
			

			}
		}
		catch (PageExpiredException pee) {
			errors.add(ExceptionConstants.PAGEEXPIREDEXCEPTION,
					new ActionMessage(ExceptionConstants.PAGEEXPIREDEXCEPTION));
		}
		catch (ServiceException e) {
			errors.add(ExceptionConstants.FRAMEWORKRUNTIMEEXCEPTION,
					new ActionMessage(
							ExceptionConstants.FRAMEWORKRUNTIMEEXCEPTION));
		}
	}
	

	private  void validateRepaymentDayRequired(HttpServletRequest request, ActionErrors errors) {
		try {

		    Integer repaymentSchudlesIndepOfMetingIsEnabled = (Integer) SessionUtils.getAttribute(
	        		LoanConstants.REPAYMENT_SCHEDULES_INDEPENDENT_OF_MEETING_IS_ENABLED, request);            
            
			if (null != repaymentSchudlesIndepOfMetingIsEnabled
					&& 0 != repaymentSchudlesIndepOfMetingIsEnabled.intValue()) {

				if (StringUtils.isNullOrEmpty(this.getMonthRank())
						|| StringUtils.isNullOrEmpty(this.getMonthWeek())
						|| StringUtils.isNullOrEmpty(this.getRecurMonth())) {
					addError(errors, "",
							LoanExceptionConstants.REPAYMENTDAYISREQUIRED, "");

				}
			}
		}
		catch (PageExpiredException pee) {
			errors.add(ExceptionConstants.PAGEEXPIREDEXCEPTION,
					new ActionMessage(ExceptionConstants.PAGEEXPIREDEXCEPTION));
		}

	}

	private  void validateIndividualLoanFields(HttpServletRequest request, ActionErrors errors) {
		try {
			CustomerBO customer = getCustomer(request);
		    Integer loanIndividualMonitoringIsEnabled = (Integer) SessionUtils.getAttribute(
	        		LoanConstants.LOANINDIVIDUALMONITORINGENABLED, request);            
            
			if (null != loanIndividualMonitoringIsEnabled
					&& 0 != loanIndividualMonitoringIsEnabled.intValue()
					&& customer.getCustomerLevel().isGroup()) {

				List<String> ids_clients_selected = getClients();
				List<LoanAccountDetailsViewHelper> listdetail = getClientDetails();
				for (LoanAccountDetailsViewHelper tempAccount : listdetail) {
					if (!ids_clients_selected.contains(tempAccount
							.getClientId())) {
						if (tempAccount.getLoanAmount() != null
								|| !tempAccount.getBusinessActivity()
										.equals("")){
							addError(errors, "", LoanExceptionConstants.LOANANDPURPOSEENTREDWITHOUTSELECTINGINDIVIDUAL,
									"");
						}
					}
				
				}
			}
		}
		catch (PageExpiredException pee) {
			errors.add(ExceptionConstants.PAGEEXPIREDEXCEPTION,
					new ActionMessage(ExceptionConstants.PAGEEXPIREDEXCEPTION));
		}
		catch (ServiceException e) {
			errors.add(ExceptionConstants.FRAMEWORKRUNTIMEEXCEPTION,
					new ActionMessage(
							ExceptionConstants.FRAMEWORKRUNTIMEEXCEPTION));
		}
	}

    private void validateRedoLoanPayments(HttpServletRequest request, ActionErrors errors) {
        try {
            if (paymentDataBeans != null && paymentDataBeans.size() > 0) {
                CustomerBO customer = getCustomer(request);
                for (PaymentDataTemplate template : paymentDataBeans) {
                    if (template.getTotalAmount() != null
                            && template.getTransactionDate() != null) {
                        if (! customer.getCustomerMeeting().getMeeting().isValidMeetingDate(
                            template.getTransactionDate(), DateUtils.getLastDayOfNextYear())) {
                            errors.add(LoanExceptionConstants.INVALIDTRANSACTIONDATE,
                                new ActionMessage(LoanExceptionConstants.INVALIDTRANSACTIONDATE));
                        }
                    }
                }
            }
        }
        catch (MeetingException e) {
            errors.add(ExceptionConstants.FRAMEWORKRUNTIMEEXCEPTION,
                    new ActionMessage(ExceptionConstants.FRAMEWORKRUNTIMEEXCEPTION));
        }
        catch (PageExpiredException e) {
            errors.add(ExceptionConstants.PAGEEXPIREDEXCEPTION,
					new ActionMessage(ExceptionConstants.PAGEEXPIREDEXCEPTION));
        }
        catch (ServiceException e) {
            errors.add(ExceptionConstants.FRAMEWORKRUNTIMEEXCEPTION,
                    new ActionMessage(ExceptionConstants.FRAMEWORKRUNTIMEEXCEPTION));
        }
    }

    protected CustomerBO getCustomer(Integer customerId) throws ServiceException {
		return getCustomerBusinessService().getCustomer(customerId);
	}

    protected CustomerBusinessService getCustomerBusinessService() {
		return (CustomerBusinessService) ServiceFactory.getInstance()
				.getBusinessService(BusinessServiceName.Customer);
	}

    private CustomerBO getCustomer(HttpServletRequest request)
                throws PageExpiredException, ServiceException {
        CustomerBO oldCustomer = (CustomerBO) SessionUtils.getAttribute(
                LoanConstants.LOANACCOUNTOWNER, request);
        Integer oldCustomerId;
        if (oldCustomer == null) {
            oldCustomerId = Integer.parseInt(getCustomerId());
        }
        else {
            oldCustomerId = oldCustomer.getCustomerId();
        }
        CustomerBO customer = getCustomer(oldCustomerId);
        customer.getPersonnel().getDisplayName();
        customer.getOffice().getOfficeName();
        // TODO: I'm not sure why we're resetting version number - need to investigate this
        if (oldCustomer != null) {
            customer.setVersionNo(oldCustomer.getVersionNo());
        }
        return customer;
    }

    public String getPerspective() {
        return perspective;
    }

    public void setPerspective(String perspective) {
        this.perspective = perspective;
    }

    	public List<String> getClients() {
		return clients;
	}

	public void setClients(List<String> clients) {
		this.clients = clients;
	}

	public String getClients(int i) {
		while (i >= clients.size())
			clients.add("");
		return clients.get(i).toString();
	}

	public void setClients(int i, String string) {
		while (this.clients.size() <= i)
			this.clients.add(new String());
		this.clients.set(i, string);
	}

	public List<LoanAccountDetailsViewHelper> getClientDetails() {
		return clientDetails;
	}

	public void setClientDetails(List<LoanAccountDetailsViewHelper> clientDetails) {
		this.clientDetails = clientDetails;
	}

	public String getFirstRepaymentDay() {
		return firstRepaymentDay;
	}

	public void setFirstRepaymentDay(String firstRepaymentDay) {
		this.firstRepaymentDay = firstRepaymentDay;
	}
    	
}
