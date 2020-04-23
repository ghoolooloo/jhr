import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ClosedOrderComponentsPage, ClosedOrderDeleteDialog, ClosedOrderUpdatePage } from './closed-order.page-object';

const expect = chai.expect;

describe('ClosedOrder e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let closedOrderComponentsPage: ClosedOrderComponentsPage;
  let closedOrderUpdatePage: ClosedOrderUpdatePage;
  let closedOrderDeleteDialog: ClosedOrderDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ClosedOrders', async () => {
    await navBarPage.goToEntity('closed-order');
    closedOrderComponentsPage = new ClosedOrderComponentsPage();
    await browser.wait(ec.visibilityOf(closedOrderComponentsPage.title), 5000);
    expect(await closedOrderComponentsPage.getTitle()).to.eq('jhrApp.closedOrder.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(closedOrderComponentsPage.entities), ec.visibilityOf(closedOrderComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ClosedOrder page', async () => {
    await closedOrderComponentsPage.clickOnCreateButton();
    closedOrderUpdatePage = new ClosedOrderUpdatePage();
    expect(await closedOrderUpdatePage.getPageTitle()).to.eq('jhrApp.closedOrder.home.createOrEditLabel');
    await closedOrderUpdatePage.cancel();
  });

  it('should create and save ClosedOrders', async () => {
    const nbButtonsBeforeCreate = await closedOrderComponentsPage.countDeleteButtons();

    await closedOrderComponentsPage.clickOnCreateButton();

    await promise.all([
      closedOrderUpdatePage.setSnInput('sn'),
      closedOrderUpdatePage.setMemberNameInput('memberName'),
      closedOrderUpdatePage.setMemberSNInput('memberSN'),
      closedOrderUpdatePage.orderTypeSelectLastOption(),
      closedOrderUpdatePage.setShopSNInput('shopSN'),
      closedOrderUpdatePage.setShopNameInput('shopName'),
      closedOrderUpdatePage.setPriceTotalInput('5'),
      closedOrderUpdatePage.setCardReduceInput('5'),
      closedOrderUpdatePage.setCardsInput('cards'),
      closedOrderUpdatePage.setRewardPointsReduceInput('5'),
      closedOrderUpdatePage.setPaymentTotalInput('5'),
      closedOrderUpdatePage.distributionPlatformSelectLastOption(),
      closedOrderUpdatePage.setDeliveryNoInput('deliveryNo'),
      closedOrderUpdatePage.setDeliveryStatusInput('5'),
      closedOrderUpdatePage.setDeliveryDescInput('deliveryDesc'),
      closedOrderUpdatePage.setDeliverierInput('deliverier'),
      closedOrderUpdatePage.setDeliverierMobileInput('deliverierMobile'),
      closedOrderUpdatePage.setDeliveryDeductFeeInput('5'),
      closedOrderUpdatePage.setDeliveryFeeInput('5'),
      closedOrderUpdatePage.setContactInput('contact'),
      closedOrderUpdatePage.sexSelectLastOption(),
      closedOrderUpdatePage.setMobileInput('mobile'),
      closedOrderUpdatePage.setCountryInput('country'),
      closedOrderUpdatePage.setProvinceInput('province'),
      closedOrderUpdatePage.setCityInput('city'),
      closedOrderUpdatePage.setDistrictInput('district'),
      closedOrderUpdatePage.offsetTypeSelectLastOption(),
      closedOrderUpdatePage.setLongitudeInput('5'),
      closedOrderUpdatePage.setLatitudeInput('5'),
      closedOrderUpdatePage.setAddressInput('address'),
      closedOrderUpdatePage.setPackingFeeInput('5'),
      closedOrderUpdatePage.paymentModeSelectLastOption(),
      closedOrderUpdatePage.setDiningDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      closedOrderUpdatePage.setRemarkInput('remark'),
      closedOrderUpdatePage.statusSelectLastOption(),
      closedOrderUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      closedOrderUpdatePage.setPaidDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      closedOrderUpdatePage.setExpiredDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      closedOrderUpdatePage.setCompletedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      closedOrderUpdatePage.setExceptionDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      closedOrderUpdatePage.setHandlerInput('handler'),
      closedOrderUpdatePage.setHandledDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      closedOrderUpdatePage.setHandledDescInput('handledDesc'),
      closedOrderUpdatePage.setApplicantInput('applicant'),
      closedOrderUpdatePage.setAppliedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      closedOrderUpdatePage.setRefundDescInput('refundDesc'),
      closedOrderUpdatePage.setRefundedByInput('refundedBy'),
      closedOrderUpdatePage.setRefundedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      closedOrderUpdatePage.setRefundAmountInput('5'),
      closedOrderUpdatePage.setReplyInput('reply')
    ]);

    expect(await closedOrderUpdatePage.getSnInput()).to.eq('sn', 'Expected Sn value to be equals to sn');
    expect(await closedOrderUpdatePage.getMemberNameInput()).to.eq('memberName', 'Expected MemberName value to be equals to memberName');
    expect(await closedOrderUpdatePage.getMemberSNInput()).to.eq('memberSN', 'Expected MemberSN value to be equals to memberSN');
    expect(await closedOrderUpdatePage.getShopSNInput()).to.eq('shopSN', 'Expected ShopSN value to be equals to shopSN');
    expect(await closedOrderUpdatePage.getShopNameInput()).to.eq('shopName', 'Expected ShopName value to be equals to shopName');
    expect(await closedOrderUpdatePage.getPriceTotalInput()).to.eq('5', 'Expected priceTotal value to be equals to 5');
    expect(await closedOrderUpdatePage.getCardReduceInput()).to.eq('5', 'Expected cardReduce value to be equals to 5');
    expect(await closedOrderUpdatePage.getCardsInput()).to.eq('cards', 'Expected Cards value to be equals to cards');
    expect(await closedOrderUpdatePage.getRewardPointsReduceInput()).to.eq('5', 'Expected rewardPointsReduce value to be equals to 5');
    expect(await closedOrderUpdatePage.getPaymentTotalInput()).to.eq('5', 'Expected paymentTotal value to be equals to 5');
    expect(await closedOrderUpdatePage.getDeliveryNoInput()).to.eq('deliveryNo', 'Expected DeliveryNo value to be equals to deliveryNo');
    expect(await closedOrderUpdatePage.getDeliveryStatusInput()).to.eq('5', 'Expected deliveryStatus value to be equals to 5');
    expect(await closedOrderUpdatePage.getDeliveryDescInput()).to.eq(
      'deliveryDesc',
      'Expected DeliveryDesc value to be equals to deliveryDesc'
    );
    expect(await closedOrderUpdatePage.getDeliverierInput()).to.eq('deliverier', 'Expected Deliverier value to be equals to deliverier');
    expect(await closedOrderUpdatePage.getDeliverierMobileInput()).to.eq(
      'deliverierMobile',
      'Expected DeliverierMobile value to be equals to deliverierMobile'
    );
    expect(await closedOrderUpdatePage.getDeliveryDeductFeeInput()).to.eq('5', 'Expected deliveryDeductFee value to be equals to 5');
    expect(await closedOrderUpdatePage.getDeliveryFeeInput()).to.eq('5', 'Expected deliveryFee value to be equals to 5');
    expect(await closedOrderUpdatePage.getContactInput()).to.eq('contact', 'Expected Contact value to be equals to contact');
    expect(await closedOrderUpdatePage.getMobileInput()).to.eq('mobile', 'Expected Mobile value to be equals to mobile');
    expect(await closedOrderUpdatePage.getCountryInput()).to.eq('country', 'Expected Country value to be equals to country');
    expect(await closedOrderUpdatePage.getProvinceInput()).to.eq('province', 'Expected Province value to be equals to province');
    expect(await closedOrderUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
    expect(await closedOrderUpdatePage.getDistrictInput()).to.eq('district', 'Expected District value to be equals to district');
    expect(await closedOrderUpdatePage.getLongitudeInput()).to.eq('5', 'Expected longitude value to be equals to 5');
    expect(await closedOrderUpdatePage.getLatitudeInput()).to.eq('5', 'Expected latitude value to be equals to 5');
    expect(await closedOrderUpdatePage.getAddressInput()).to.eq('address', 'Expected Address value to be equals to address');
    expect(await closedOrderUpdatePage.getPackingFeeInput()).to.eq('5', 'Expected packingFee value to be equals to 5');
    expect(await closedOrderUpdatePage.getDiningDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected diningDate value to be equals to 2000-12-31'
    );
    expect(await closedOrderUpdatePage.getRemarkInput()).to.eq('remark', 'Expected Remark value to be equals to remark');
    expect(await closedOrderUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await closedOrderUpdatePage.getPaidDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected paidDate value to be equals to 2000-12-31'
    );
    expect(await closedOrderUpdatePage.getExpiredDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected expiredDate value to be equals to 2000-12-31'
    );
    expect(await closedOrderUpdatePage.getCompletedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected completedDate value to be equals to 2000-12-31'
    );
    expect(await closedOrderUpdatePage.getExceptionDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected exceptionDate value to be equals to 2000-12-31'
    );
    expect(await closedOrderUpdatePage.getHandlerInput()).to.eq('handler', 'Expected Handler value to be equals to handler');
    expect(await closedOrderUpdatePage.getHandledDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected handledDate value to be equals to 2000-12-31'
    );
    expect(await closedOrderUpdatePage.getHandledDescInput()).to.eq(
      'handledDesc',
      'Expected HandledDesc value to be equals to handledDesc'
    );
    expect(await closedOrderUpdatePage.getApplicantInput()).to.eq('applicant', 'Expected Applicant value to be equals to applicant');
    expect(await closedOrderUpdatePage.getAppliedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected appliedDate value to be equals to 2000-12-31'
    );
    expect(await closedOrderUpdatePage.getRefundDescInput()).to.eq('refundDesc', 'Expected RefundDesc value to be equals to refundDesc');
    expect(await closedOrderUpdatePage.getRefundedByInput()).to.eq('refundedBy', 'Expected RefundedBy value to be equals to refundedBy');
    expect(await closedOrderUpdatePage.getRefundedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected refundedDate value to be equals to 2000-12-31'
    );
    expect(await closedOrderUpdatePage.getRefundAmountInput()).to.eq('5', 'Expected refundAmount value to be equals to 5');
    expect(await closedOrderUpdatePage.getReplyInput()).to.eq('reply', 'Expected Reply value to be equals to reply');
    const selectedPassed = closedOrderUpdatePage.getPassedInput();
    if (await selectedPassed.isSelected()) {
      await closedOrderUpdatePage.getPassedInput().click();
      expect(await closedOrderUpdatePage.getPassedInput().isSelected(), 'Expected passed not to be selected').to.be.false;
    } else {
      await closedOrderUpdatePage.getPassedInput().click();
      expect(await closedOrderUpdatePage.getPassedInput().isSelected(), 'Expected passed to be selected').to.be.true;
    }

    await closedOrderUpdatePage.save();
    expect(await closedOrderUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await closedOrderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last ClosedOrder', async () => {
    const nbButtonsBeforeDelete = await closedOrderComponentsPage.countDeleteButtons();
    await closedOrderComponentsPage.clickOnLastDeleteButton();

    closedOrderDeleteDialog = new ClosedOrderDeleteDialog();
    expect(await closedOrderDeleteDialog.getDialogTitle()).to.eq('jhrApp.closedOrder.delete.question');
    await closedOrderDeleteDialog.clickOnConfirmButton();

    expect(await closedOrderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
