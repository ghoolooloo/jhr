import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { RefundOrderComponentsPage, RefundOrderDeleteDialog, RefundOrderUpdatePage } from './refund-order.page-object';

const expect = chai.expect;

describe('RefundOrder e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let refundOrderComponentsPage: RefundOrderComponentsPage;
  let refundOrderUpdatePage: RefundOrderUpdatePage;
  let refundOrderDeleteDialog: RefundOrderDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load RefundOrders', async () => {
    await navBarPage.goToEntity('refund-order');
    refundOrderComponentsPage = new RefundOrderComponentsPage();
    await browser.wait(ec.visibilityOf(refundOrderComponentsPage.title), 5000);
    expect(await refundOrderComponentsPage.getTitle()).to.eq('jhrApp.refundOrder.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(refundOrderComponentsPage.entities), ec.visibilityOf(refundOrderComponentsPage.noResult)),
      1000
    );
  });

  it('should load create RefundOrder page', async () => {
    await refundOrderComponentsPage.clickOnCreateButton();
    refundOrderUpdatePage = new RefundOrderUpdatePage();
    expect(await refundOrderUpdatePage.getPageTitle()).to.eq('jhrApp.refundOrder.home.createOrEditLabel');
    await refundOrderUpdatePage.cancel();
  });

  it('should create and save RefundOrders', async () => {
    const nbButtonsBeforeCreate = await refundOrderComponentsPage.countDeleteButtons();

    await refundOrderComponentsPage.clickOnCreateButton();

    await promise.all([
      refundOrderUpdatePage.setSnInput('sn'),
      refundOrderUpdatePage.setMemberNameInput('memberName'),
      refundOrderUpdatePage.setMemberSNInput('memberSN'),
      refundOrderUpdatePage.orderTypeSelectLastOption(),
      refundOrderUpdatePage.setShopSNInput('shopSN'),
      refundOrderUpdatePage.setShopNameInput('shopName'),
      refundOrderUpdatePage.setPriceTotalInput('5'),
      refundOrderUpdatePage.setCardReduceInput('5'),
      refundOrderUpdatePage.setCardsInput('cards'),
      refundOrderUpdatePage.setRewardPointsReduceInput('5'),
      refundOrderUpdatePage.setPaymentTotalInput('5'),
      refundOrderUpdatePage.distributionPlatformSelectLastOption(),
      refundOrderUpdatePage.setDeliveryNoInput('deliveryNo'),
      refundOrderUpdatePage.setDeliveryStatusInput('5'),
      refundOrderUpdatePage.setDeliveryDescInput('deliveryDesc'),
      refundOrderUpdatePage.setDeliverierInput('deliverier'),
      refundOrderUpdatePage.setDeliverierMobileInput('deliverierMobile'),
      refundOrderUpdatePage.setDeliveryDeductFeeInput('5'),
      refundOrderUpdatePage.setDeliveryFeeInput('5'),
      refundOrderUpdatePage.setContactInput('contact'),
      refundOrderUpdatePage.sexSelectLastOption(),
      refundOrderUpdatePage.setMobileInput('mobile'),
      refundOrderUpdatePage.setCountryInput('country'),
      refundOrderUpdatePage.setProvinceInput('province'),
      refundOrderUpdatePage.setCityInput('city'),
      refundOrderUpdatePage.setDistrictInput('district'),
      refundOrderUpdatePage.offsetTypeSelectLastOption(),
      refundOrderUpdatePage.setLongitudeInput('5'),
      refundOrderUpdatePage.setLatitudeInput('5'),
      refundOrderUpdatePage.setAddressInput('address'),
      refundOrderUpdatePage.setPackingFeeInput('5'),
      refundOrderUpdatePage.paymentModeSelectLastOption(),
      refundOrderUpdatePage.setDiningDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      refundOrderUpdatePage.setRemarkInput('remark'),
      refundOrderUpdatePage.statusSelectLastOption(),
      refundOrderUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      refundOrderUpdatePage.setPaidDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      refundOrderUpdatePage.setCompletedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      refundOrderUpdatePage.setExceptionDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      refundOrderUpdatePage.setHandlerInput('handler'),
      refundOrderUpdatePage.setHandledDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      refundOrderUpdatePage.setHandledDescInput('handledDesc'),
      refundOrderUpdatePage.setApplicantInput('applicant'),
      refundOrderUpdatePage.setAppliedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      refundOrderUpdatePage.setRefundDescInput('refundDesc'),
      refundOrderUpdatePage.setRefundedByInput('refundedBy'),
      refundOrderUpdatePage.setRefundedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      refundOrderUpdatePage.setRefundAmountInput('5'),
      refundOrderUpdatePage.setReplyInput('reply')
    ]);

    expect(await refundOrderUpdatePage.getSnInput()).to.eq('sn', 'Expected Sn value to be equals to sn');
    expect(await refundOrderUpdatePage.getMemberNameInput()).to.eq('memberName', 'Expected MemberName value to be equals to memberName');
    expect(await refundOrderUpdatePage.getMemberSNInput()).to.eq('memberSN', 'Expected MemberSN value to be equals to memberSN');
    expect(await refundOrderUpdatePage.getShopSNInput()).to.eq('shopSN', 'Expected ShopSN value to be equals to shopSN');
    expect(await refundOrderUpdatePage.getShopNameInput()).to.eq('shopName', 'Expected ShopName value to be equals to shopName');
    expect(await refundOrderUpdatePage.getPriceTotalInput()).to.eq('5', 'Expected priceTotal value to be equals to 5');
    expect(await refundOrderUpdatePage.getCardReduceInput()).to.eq('5', 'Expected cardReduce value to be equals to 5');
    expect(await refundOrderUpdatePage.getCardsInput()).to.eq('cards', 'Expected Cards value to be equals to cards');
    expect(await refundOrderUpdatePage.getRewardPointsReduceInput()).to.eq('5', 'Expected rewardPointsReduce value to be equals to 5');
    expect(await refundOrderUpdatePage.getPaymentTotalInput()).to.eq('5', 'Expected paymentTotal value to be equals to 5');
    expect(await refundOrderUpdatePage.getDeliveryNoInput()).to.eq('deliveryNo', 'Expected DeliveryNo value to be equals to deliveryNo');
    expect(await refundOrderUpdatePage.getDeliveryStatusInput()).to.eq('5', 'Expected deliveryStatus value to be equals to 5');
    expect(await refundOrderUpdatePage.getDeliveryDescInput()).to.eq(
      'deliveryDesc',
      'Expected DeliveryDesc value to be equals to deliveryDesc'
    );
    expect(await refundOrderUpdatePage.getDeliverierInput()).to.eq('deliverier', 'Expected Deliverier value to be equals to deliverier');
    expect(await refundOrderUpdatePage.getDeliverierMobileInput()).to.eq(
      'deliverierMobile',
      'Expected DeliverierMobile value to be equals to deliverierMobile'
    );
    expect(await refundOrderUpdatePage.getDeliveryDeductFeeInput()).to.eq('5', 'Expected deliveryDeductFee value to be equals to 5');
    expect(await refundOrderUpdatePage.getDeliveryFeeInput()).to.eq('5', 'Expected deliveryFee value to be equals to 5');
    expect(await refundOrderUpdatePage.getContactInput()).to.eq('contact', 'Expected Contact value to be equals to contact');
    expect(await refundOrderUpdatePage.getMobileInput()).to.eq('mobile', 'Expected Mobile value to be equals to mobile');
    expect(await refundOrderUpdatePage.getCountryInput()).to.eq('country', 'Expected Country value to be equals to country');
    expect(await refundOrderUpdatePage.getProvinceInput()).to.eq('province', 'Expected Province value to be equals to province');
    expect(await refundOrderUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
    expect(await refundOrderUpdatePage.getDistrictInput()).to.eq('district', 'Expected District value to be equals to district');
    expect(await refundOrderUpdatePage.getLongitudeInput()).to.eq('5', 'Expected longitude value to be equals to 5');
    expect(await refundOrderUpdatePage.getLatitudeInput()).to.eq('5', 'Expected latitude value to be equals to 5');
    expect(await refundOrderUpdatePage.getAddressInput()).to.eq('address', 'Expected Address value to be equals to address');
    expect(await refundOrderUpdatePage.getPackingFeeInput()).to.eq('5', 'Expected packingFee value to be equals to 5');
    expect(await refundOrderUpdatePage.getDiningDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected diningDate value to be equals to 2000-12-31'
    );
    expect(await refundOrderUpdatePage.getRemarkInput()).to.eq('remark', 'Expected Remark value to be equals to remark');
    expect(await refundOrderUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await refundOrderUpdatePage.getPaidDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected paidDate value to be equals to 2000-12-31'
    );
    expect(await refundOrderUpdatePage.getCompletedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected completedDate value to be equals to 2000-12-31'
    );
    expect(await refundOrderUpdatePage.getExceptionDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected exceptionDate value to be equals to 2000-12-31'
    );
    expect(await refundOrderUpdatePage.getHandlerInput()).to.eq('handler', 'Expected Handler value to be equals to handler');
    expect(await refundOrderUpdatePage.getHandledDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected handledDate value to be equals to 2000-12-31'
    );
    expect(await refundOrderUpdatePage.getHandledDescInput()).to.eq(
      'handledDesc',
      'Expected HandledDesc value to be equals to handledDesc'
    );
    expect(await refundOrderUpdatePage.getApplicantInput()).to.eq('applicant', 'Expected Applicant value to be equals to applicant');
    expect(await refundOrderUpdatePage.getAppliedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected appliedDate value to be equals to 2000-12-31'
    );
    expect(await refundOrderUpdatePage.getRefundDescInput()).to.eq('refundDesc', 'Expected RefundDesc value to be equals to refundDesc');
    expect(await refundOrderUpdatePage.getRefundedByInput()).to.eq('refundedBy', 'Expected RefundedBy value to be equals to refundedBy');
    expect(await refundOrderUpdatePage.getRefundedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected refundedDate value to be equals to 2000-12-31'
    );
    expect(await refundOrderUpdatePage.getRefundAmountInput()).to.eq('5', 'Expected refundAmount value to be equals to 5');
    expect(await refundOrderUpdatePage.getReplyInput()).to.eq('reply', 'Expected Reply value to be equals to reply');
    const selectedPassed = refundOrderUpdatePage.getPassedInput();
    if (await selectedPassed.isSelected()) {
      await refundOrderUpdatePage.getPassedInput().click();
      expect(await refundOrderUpdatePage.getPassedInput().isSelected(), 'Expected passed not to be selected').to.be.false;
    } else {
      await refundOrderUpdatePage.getPassedInput().click();
      expect(await refundOrderUpdatePage.getPassedInput().isSelected(), 'Expected passed to be selected').to.be.true;
    }

    await refundOrderUpdatePage.save();
    expect(await refundOrderUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await refundOrderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last RefundOrder', async () => {
    const nbButtonsBeforeDelete = await refundOrderComponentsPage.countDeleteButtons();
    await refundOrderComponentsPage.clickOnLastDeleteButton();

    refundOrderDeleteDialog = new RefundOrderDeleteDialog();
    expect(await refundOrderDeleteDialog.getDialogTitle()).to.eq('jhrApp.refundOrder.delete.question');
    await refundOrderDeleteDialog.clickOnConfirmButton();

    expect(await refundOrderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
