import { element, by, ElementFinder } from 'protractor';

export class RefundOrderComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-refund-order div table .btn-danger'));
  title = element.all(by.css('jhi-refund-order div h2#page-heading span')).first();
  noResult = element(by.id('no-result'));
  entities = element(by.id('entities'));

  async clickOnCreateButton(): Promise<void> {
    await this.createButton.click();
  }

  async clickOnLastDeleteButton(): Promise<void> {
    await this.deleteButtons.last().click();
  }

  async countDeleteButtons(): Promise<number> {
    return this.deleteButtons.count();
  }

  async getTitle(): Promise<string> {
    return this.title.getAttribute('jhiTranslate');
  }
}

export class RefundOrderUpdatePage {
  pageTitle = element(by.id('jhi-refund-order-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  snInput = element(by.id('field_sn'));
  memberNameInput = element(by.id('field_memberName'));
  memberSNInput = element(by.id('field_memberSN'));
  orderTypeSelect = element(by.id('field_orderType'));
  shopSNInput = element(by.id('field_shopSN'));
  shopNameInput = element(by.id('field_shopName'));
  priceTotalInput = element(by.id('field_priceTotal'));
  cardReduceInput = element(by.id('field_cardReduce'));
  cardsInput = element(by.id('field_cards'));
  rewardPointsReduceInput = element(by.id('field_rewardPointsReduce'));
  paymentTotalInput = element(by.id('field_paymentTotal'));
  distributionPlatformSelect = element(by.id('field_distributionPlatform'));
  deliveryNoInput = element(by.id('field_deliveryNo'));
  deliveryStatusInput = element(by.id('field_deliveryStatus'));
  deliveryDescInput = element(by.id('field_deliveryDesc'));
  deliverierInput = element(by.id('field_deliverier'));
  deliverierMobileInput = element(by.id('field_deliverierMobile'));
  deliveryDeductFeeInput = element(by.id('field_deliveryDeductFee'));
  deliveryFeeInput = element(by.id('field_deliveryFee'));
  contactInput = element(by.id('field_contact'));
  sexSelect = element(by.id('field_sex'));
  mobileInput = element(by.id('field_mobile'));
  countryInput = element(by.id('field_country'));
  provinceInput = element(by.id('field_province'));
  cityInput = element(by.id('field_city'));
  districtInput = element(by.id('field_district'));
  offsetTypeSelect = element(by.id('field_offsetType'));
  longitudeInput = element(by.id('field_longitude'));
  latitudeInput = element(by.id('field_latitude'));
  addressInput = element(by.id('field_address'));
  packingFeeInput = element(by.id('field_packingFee'));
  paymentModeSelect = element(by.id('field_paymentMode'));
  diningDateInput = element(by.id('field_diningDate'));
  remarkInput = element(by.id('field_remark'));
  statusSelect = element(by.id('field_status'));
  createdDateInput = element(by.id('field_createdDate'));
  paidDateInput = element(by.id('field_paidDate'));
  completedDateInput = element(by.id('field_completedDate'));
  exceptionDateInput = element(by.id('field_exceptionDate'));
  handlerInput = element(by.id('field_handler'));
  handledDateInput = element(by.id('field_handledDate'));
  handledDescInput = element(by.id('field_handledDesc'));
  applicantInput = element(by.id('field_applicant'));
  appliedDateInput = element(by.id('field_appliedDate'));
  refundDescInput = element(by.id('field_refundDesc'));
  refundedByInput = element(by.id('field_refundedBy'));
  refundedDateInput = element(by.id('field_refundedDate'));
  refundAmountInput = element(by.id('field_refundAmount'));
  replyInput = element(by.id('field_reply'));
  passedInput = element(by.id('field_passed'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSnInput(sn: string): Promise<void> {
    await this.snInput.sendKeys(sn);
  }

  async getSnInput(): Promise<string> {
    return await this.snInput.getAttribute('value');
  }

  async setMemberNameInput(memberName: string): Promise<void> {
    await this.memberNameInput.sendKeys(memberName);
  }

  async getMemberNameInput(): Promise<string> {
    return await this.memberNameInput.getAttribute('value');
  }

  async setMemberSNInput(memberSN: string): Promise<void> {
    await this.memberSNInput.sendKeys(memberSN);
  }

  async getMemberSNInput(): Promise<string> {
    return await this.memberSNInput.getAttribute('value');
  }

  async setOrderTypeSelect(orderType: string): Promise<void> {
    await this.orderTypeSelect.sendKeys(orderType);
  }

  async getOrderTypeSelect(): Promise<string> {
    return await this.orderTypeSelect.element(by.css('option:checked')).getText();
  }

  async orderTypeSelectLastOption(): Promise<void> {
    await this.orderTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setShopSNInput(shopSN: string): Promise<void> {
    await this.shopSNInput.sendKeys(shopSN);
  }

  async getShopSNInput(): Promise<string> {
    return await this.shopSNInput.getAttribute('value');
  }

  async setShopNameInput(shopName: string): Promise<void> {
    await this.shopNameInput.sendKeys(shopName);
  }

  async getShopNameInput(): Promise<string> {
    return await this.shopNameInput.getAttribute('value');
  }

  async setPriceTotalInput(priceTotal: string): Promise<void> {
    await this.priceTotalInput.sendKeys(priceTotal);
  }

  async getPriceTotalInput(): Promise<string> {
    return await this.priceTotalInput.getAttribute('value');
  }

  async setCardReduceInput(cardReduce: string): Promise<void> {
    await this.cardReduceInput.sendKeys(cardReduce);
  }

  async getCardReduceInput(): Promise<string> {
    return await this.cardReduceInput.getAttribute('value');
  }

  async setCardsInput(cards: string): Promise<void> {
    await this.cardsInput.sendKeys(cards);
  }

  async getCardsInput(): Promise<string> {
    return await this.cardsInput.getAttribute('value');
  }

  async setRewardPointsReduceInput(rewardPointsReduce: string): Promise<void> {
    await this.rewardPointsReduceInput.sendKeys(rewardPointsReduce);
  }

  async getRewardPointsReduceInput(): Promise<string> {
    return await this.rewardPointsReduceInput.getAttribute('value');
  }

  async setPaymentTotalInput(paymentTotal: string): Promise<void> {
    await this.paymentTotalInput.sendKeys(paymentTotal);
  }

  async getPaymentTotalInput(): Promise<string> {
    return await this.paymentTotalInput.getAttribute('value');
  }

  async setDistributionPlatformSelect(distributionPlatform: string): Promise<void> {
    await this.distributionPlatformSelect.sendKeys(distributionPlatform);
  }

  async getDistributionPlatformSelect(): Promise<string> {
    return await this.distributionPlatformSelect.element(by.css('option:checked')).getText();
  }

  async distributionPlatformSelectLastOption(): Promise<void> {
    await this.distributionPlatformSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setDeliveryNoInput(deliveryNo: string): Promise<void> {
    await this.deliveryNoInput.sendKeys(deliveryNo);
  }

  async getDeliveryNoInput(): Promise<string> {
    return await this.deliveryNoInput.getAttribute('value');
  }

  async setDeliveryStatusInput(deliveryStatus: string): Promise<void> {
    await this.deliveryStatusInput.sendKeys(deliveryStatus);
  }

  async getDeliveryStatusInput(): Promise<string> {
    return await this.deliveryStatusInput.getAttribute('value');
  }

  async setDeliveryDescInput(deliveryDesc: string): Promise<void> {
    await this.deliveryDescInput.sendKeys(deliveryDesc);
  }

  async getDeliveryDescInput(): Promise<string> {
    return await this.deliveryDescInput.getAttribute('value');
  }

  async setDeliverierInput(deliverier: string): Promise<void> {
    await this.deliverierInput.sendKeys(deliverier);
  }

  async getDeliverierInput(): Promise<string> {
    return await this.deliverierInput.getAttribute('value');
  }

  async setDeliverierMobileInput(deliverierMobile: string): Promise<void> {
    await this.deliverierMobileInput.sendKeys(deliverierMobile);
  }

  async getDeliverierMobileInput(): Promise<string> {
    return await this.deliverierMobileInput.getAttribute('value');
  }

  async setDeliveryDeductFeeInput(deliveryDeductFee: string): Promise<void> {
    await this.deliveryDeductFeeInput.sendKeys(deliveryDeductFee);
  }

  async getDeliveryDeductFeeInput(): Promise<string> {
    return await this.deliveryDeductFeeInput.getAttribute('value');
  }

  async setDeliveryFeeInput(deliveryFee: string): Promise<void> {
    await this.deliveryFeeInput.sendKeys(deliveryFee);
  }

  async getDeliveryFeeInput(): Promise<string> {
    return await this.deliveryFeeInput.getAttribute('value');
  }

  async setContactInput(contact: string): Promise<void> {
    await this.contactInput.sendKeys(contact);
  }

  async getContactInput(): Promise<string> {
    return await this.contactInput.getAttribute('value');
  }

  async setSexSelect(sex: string): Promise<void> {
    await this.sexSelect.sendKeys(sex);
  }

  async getSexSelect(): Promise<string> {
    return await this.sexSelect.element(by.css('option:checked')).getText();
  }

  async sexSelectLastOption(): Promise<void> {
    await this.sexSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setMobileInput(mobile: string): Promise<void> {
    await this.mobileInput.sendKeys(mobile);
  }

  async getMobileInput(): Promise<string> {
    return await this.mobileInput.getAttribute('value');
  }

  async setCountryInput(country: string): Promise<void> {
    await this.countryInput.sendKeys(country);
  }

  async getCountryInput(): Promise<string> {
    return await this.countryInput.getAttribute('value');
  }

  async setProvinceInput(province: string): Promise<void> {
    await this.provinceInput.sendKeys(province);
  }

  async getProvinceInput(): Promise<string> {
    return await this.provinceInput.getAttribute('value');
  }

  async setCityInput(city: string): Promise<void> {
    await this.cityInput.sendKeys(city);
  }

  async getCityInput(): Promise<string> {
    return await this.cityInput.getAttribute('value');
  }

  async setDistrictInput(district: string): Promise<void> {
    await this.districtInput.sendKeys(district);
  }

  async getDistrictInput(): Promise<string> {
    return await this.districtInput.getAttribute('value');
  }

  async setOffsetTypeSelect(offsetType: string): Promise<void> {
    await this.offsetTypeSelect.sendKeys(offsetType);
  }

  async getOffsetTypeSelect(): Promise<string> {
    return await this.offsetTypeSelect.element(by.css('option:checked')).getText();
  }

  async offsetTypeSelectLastOption(): Promise<void> {
    await this.offsetTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setLongitudeInput(longitude: string): Promise<void> {
    await this.longitudeInput.sendKeys(longitude);
  }

  async getLongitudeInput(): Promise<string> {
    return await this.longitudeInput.getAttribute('value');
  }

  async setLatitudeInput(latitude: string): Promise<void> {
    await this.latitudeInput.sendKeys(latitude);
  }

  async getLatitudeInput(): Promise<string> {
    return await this.latitudeInput.getAttribute('value');
  }

  async setAddressInput(address: string): Promise<void> {
    await this.addressInput.sendKeys(address);
  }

  async getAddressInput(): Promise<string> {
    return await this.addressInput.getAttribute('value');
  }

  async setPackingFeeInput(packingFee: string): Promise<void> {
    await this.packingFeeInput.sendKeys(packingFee);
  }

  async getPackingFeeInput(): Promise<string> {
    return await this.packingFeeInput.getAttribute('value');
  }

  async setPaymentModeSelect(paymentMode: string): Promise<void> {
    await this.paymentModeSelect.sendKeys(paymentMode);
  }

  async getPaymentModeSelect(): Promise<string> {
    return await this.paymentModeSelect.element(by.css('option:checked')).getText();
  }

  async paymentModeSelectLastOption(): Promise<void> {
    await this.paymentModeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setDiningDateInput(diningDate: string): Promise<void> {
    await this.diningDateInput.sendKeys(diningDate);
  }

  async getDiningDateInput(): Promise<string> {
    return await this.diningDateInput.getAttribute('value');
  }

  async setRemarkInput(remark: string): Promise<void> {
    await this.remarkInput.sendKeys(remark);
  }

  async getRemarkInput(): Promise<string> {
    return await this.remarkInput.getAttribute('value');
  }

  async setStatusSelect(status: string): Promise<void> {
    await this.statusSelect.sendKeys(status);
  }

  async getStatusSelect(): Promise<string> {
    return await this.statusSelect.element(by.css('option:checked')).getText();
  }

  async statusSelectLastOption(): Promise<void> {
    await this.statusSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setCreatedDateInput(createdDate: string): Promise<void> {
    await this.createdDateInput.sendKeys(createdDate);
  }

  async getCreatedDateInput(): Promise<string> {
    return await this.createdDateInput.getAttribute('value');
  }

  async setPaidDateInput(paidDate: string): Promise<void> {
    await this.paidDateInput.sendKeys(paidDate);
  }

  async getPaidDateInput(): Promise<string> {
    return await this.paidDateInput.getAttribute('value');
  }

  async setCompletedDateInput(completedDate: string): Promise<void> {
    await this.completedDateInput.sendKeys(completedDate);
  }

  async getCompletedDateInput(): Promise<string> {
    return await this.completedDateInput.getAttribute('value');
  }

  async setExceptionDateInput(exceptionDate: string): Promise<void> {
    await this.exceptionDateInput.sendKeys(exceptionDate);
  }

  async getExceptionDateInput(): Promise<string> {
    return await this.exceptionDateInput.getAttribute('value');
  }

  async setHandlerInput(handler: string): Promise<void> {
    await this.handlerInput.sendKeys(handler);
  }

  async getHandlerInput(): Promise<string> {
    return await this.handlerInput.getAttribute('value');
  }

  async setHandledDateInput(handledDate: string): Promise<void> {
    await this.handledDateInput.sendKeys(handledDate);
  }

  async getHandledDateInput(): Promise<string> {
    return await this.handledDateInput.getAttribute('value');
  }

  async setHandledDescInput(handledDesc: string): Promise<void> {
    await this.handledDescInput.sendKeys(handledDesc);
  }

  async getHandledDescInput(): Promise<string> {
    return await this.handledDescInput.getAttribute('value');
  }

  async setApplicantInput(applicant: string): Promise<void> {
    await this.applicantInput.sendKeys(applicant);
  }

  async getApplicantInput(): Promise<string> {
    return await this.applicantInput.getAttribute('value');
  }

  async setAppliedDateInput(appliedDate: string): Promise<void> {
    await this.appliedDateInput.sendKeys(appliedDate);
  }

  async getAppliedDateInput(): Promise<string> {
    return await this.appliedDateInput.getAttribute('value');
  }

  async setRefundDescInput(refundDesc: string): Promise<void> {
    await this.refundDescInput.sendKeys(refundDesc);
  }

  async getRefundDescInput(): Promise<string> {
    return await this.refundDescInput.getAttribute('value');
  }

  async setRefundedByInput(refundedBy: string): Promise<void> {
    await this.refundedByInput.sendKeys(refundedBy);
  }

  async getRefundedByInput(): Promise<string> {
    return await this.refundedByInput.getAttribute('value');
  }

  async setRefundedDateInput(refundedDate: string): Promise<void> {
    await this.refundedDateInput.sendKeys(refundedDate);
  }

  async getRefundedDateInput(): Promise<string> {
    return await this.refundedDateInput.getAttribute('value');
  }

  async setRefundAmountInput(refundAmount: string): Promise<void> {
    await this.refundAmountInput.sendKeys(refundAmount);
  }

  async getRefundAmountInput(): Promise<string> {
    return await this.refundAmountInput.getAttribute('value');
  }

  async setReplyInput(reply: string): Promise<void> {
    await this.replyInput.sendKeys(reply);
  }

  async getReplyInput(): Promise<string> {
    return await this.replyInput.getAttribute('value');
  }

  getPassedInput(): ElementFinder {
    return this.passedInput;
  }

  async save(): Promise<void> {
    await this.saveButton.click();
  }

  async cancel(): Promise<void> {
    await this.cancelButton.click();
  }

  getSaveButton(): ElementFinder {
    return this.saveButton;
  }
}

export class RefundOrderDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-refundOrder-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-refundOrder'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
