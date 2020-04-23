import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { ExceptionOrderComponentsPage, ExceptionOrderDeleteDialog, ExceptionOrderUpdatePage } from './exception-order.page-object';

const expect = chai.expect;

describe('ExceptionOrder e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let exceptionOrderComponentsPage: ExceptionOrderComponentsPage;
  let exceptionOrderUpdatePage: ExceptionOrderUpdatePage;
  let exceptionOrderDeleteDialog: ExceptionOrderDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load ExceptionOrders', async () => {
    await navBarPage.goToEntity('exception-order');
    exceptionOrderComponentsPage = new ExceptionOrderComponentsPage();
    await browser.wait(ec.visibilityOf(exceptionOrderComponentsPage.title), 5000);
    expect(await exceptionOrderComponentsPage.getTitle()).to.eq('jhrApp.exceptionOrder.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(exceptionOrderComponentsPage.entities), ec.visibilityOf(exceptionOrderComponentsPage.noResult)),
      1000
    );
  });

  it('should load create ExceptionOrder page', async () => {
    await exceptionOrderComponentsPage.clickOnCreateButton();
    exceptionOrderUpdatePage = new ExceptionOrderUpdatePage();
    expect(await exceptionOrderUpdatePage.getPageTitle()).to.eq('jhrApp.exceptionOrder.home.createOrEditLabel');
    await exceptionOrderUpdatePage.cancel();
  });

  it('should create and save ExceptionOrders', async () => {
    const nbButtonsBeforeCreate = await exceptionOrderComponentsPage.countDeleteButtons();

    await exceptionOrderComponentsPage.clickOnCreateButton();

    await promise.all([
      exceptionOrderUpdatePage.setSnInput('sn'),
      exceptionOrderUpdatePage.setMemberNameInput('memberName'),
      exceptionOrderUpdatePage.setMemberSNInput('memberSN'),
      exceptionOrderUpdatePage.orderTypeSelectLastOption(),
      exceptionOrderUpdatePage.setShopSNInput('shopSN'),
      exceptionOrderUpdatePage.setShopNameInput('shopName'),
      exceptionOrderUpdatePage.setPriceTotalInput('5'),
      exceptionOrderUpdatePage.setCardReduceInput('5'),
      exceptionOrderUpdatePage.setCardsInput('cards'),
      exceptionOrderUpdatePage.setRewardPointsReduceInput('5'),
      exceptionOrderUpdatePage.setPaymentTotalInput('5'),
      exceptionOrderUpdatePage.distributionPlatformSelectLastOption(),
      exceptionOrderUpdatePage.setDeliveryNoInput('deliveryNo'),
      exceptionOrderUpdatePage.setDeliveryStatusInput('5'),
      exceptionOrderUpdatePage.setDeliveryDescInput('deliveryDesc'),
      exceptionOrderUpdatePage.setDeliverierInput('deliverier'),
      exceptionOrderUpdatePage.setDeliverierMobileInput('deliverierMobile'),
      exceptionOrderUpdatePage.setDeliveryDeductFeeInput('5'),
      exceptionOrderUpdatePage.setDeliveryFeeInput('5'),
      exceptionOrderUpdatePage.setContactInput('contact'),
      exceptionOrderUpdatePage.sexSelectLastOption(),
      exceptionOrderUpdatePage.setMobileInput('mobile'),
      exceptionOrderUpdatePage.setCountryInput('country'),
      exceptionOrderUpdatePage.setProvinceInput('province'),
      exceptionOrderUpdatePage.setCityInput('city'),
      exceptionOrderUpdatePage.setDistrictInput('district'),
      exceptionOrderUpdatePage.offsetTypeSelectLastOption(),
      exceptionOrderUpdatePage.setLongitudeInput('5'),
      exceptionOrderUpdatePage.setLatitudeInput('5'),
      exceptionOrderUpdatePage.setAddressInput('address'),
      exceptionOrderUpdatePage.setPackingFeeInput('5'),
      exceptionOrderUpdatePage.paymentModeSelectLastOption(),
      exceptionOrderUpdatePage.setDiningDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      exceptionOrderUpdatePage.setRemarkInput('remark'),
      exceptionOrderUpdatePage.statusSelectLastOption(),
      exceptionOrderUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      exceptionOrderUpdatePage.setPaidDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      exceptionOrderUpdatePage.setExceptionDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      exceptionOrderUpdatePage.setHandlerInput('handler'),
      exceptionOrderUpdatePage.setHandledDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      exceptionOrderUpdatePage.setHandledDescInput('handledDesc')
    ]);

    expect(await exceptionOrderUpdatePage.getSnInput()).to.eq('sn', 'Expected Sn value to be equals to sn');
    expect(await exceptionOrderUpdatePage.getMemberNameInput()).to.eq('memberName', 'Expected MemberName value to be equals to memberName');
    expect(await exceptionOrderUpdatePage.getMemberSNInput()).to.eq('memberSN', 'Expected MemberSN value to be equals to memberSN');
    expect(await exceptionOrderUpdatePage.getShopSNInput()).to.eq('shopSN', 'Expected ShopSN value to be equals to shopSN');
    expect(await exceptionOrderUpdatePage.getShopNameInput()).to.eq('shopName', 'Expected ShopName value to be equals to shopName');
    expect(await exceptionOrderUpdatePage.getPriceTotalInput()).to.eq('5', 'Expected priceTotal value to be equals to 5');
    expect(await exceptionOrderUpdatePage.getCardReduceInput()).to.eq('5', 'Expected cardReduce value to be equals to 5');
    expect(await exceptionOrderUpdatePage.getCardsInput()).to.eq('cards', 'Expected Cards value to be equals to cards');
    expect(await exceptionOrderUpdatePage.getRewardPointsReduceInput()).to.eq('5', 'Expected rewardPointsReduce value to be equals to 5');
    expect(await exceptionOrderUpdatePage.getPaymentTotalInput()).to.eq('5', 'Expected paymentTotal value to be equals to 5');
    expect(await exceptionOrderUpdatePage.getDeliveryNoInput()).to.eq('deliveryNo', 'Expected DeliveryNo value to be equals to deliveryNo');
    expect(await exceptionOrderUpdatePage.getDeliveryStatusInput()).to.eq('5', 'Expected deliveryStatus value to be equals to 5');
    expect(await exceptionOrderUpdatePage.getDeliveryDescInput()).to.eq(
      'deliveryDesc',
      'Expected DeliveryDesc value to be equals to deliveryDesc'
    );
    expect(await exceptionOrderUpdatePage.getDeliverierInput()).to.eq('deliverier', 'Expected Deliverier value to be equals to deliverier');
    expect(await exceptionOrderUpdatePage.getDeliverierMobileInput()).to.eq(
      'deliverierMobile',
      'Expected DeliverierMobile value to be equals to deliverierMobile'
    );
    expect(await exceptionOrderUpdatePage.getDeliveryDeductFeeInput()).to.eq('5', 'Expected deliveryDeductFee value to be equals to 5');
    expect(await exceptionOrderUpdatePage.getDeliveryFeeInput()).to.eq('5', 'Expected deliveryFee value to be equals to 5');
    expect(await exceptionOrderUpdatePage.getContactInput()).to.eq('contact', 'Expected Contact value to be equals to contact');
    expect(await exceptionOrderUpdatePage.getMobileInput()).to.eq('mobile', 'Expected Mobile value to be equals to mobile');
    expect(await exceptionOrderUpdatePage.getCountryInput()).to.eq('country', 'Expected Country value to be equals to country');
    expect(await exceptionOrderUpdatePage.getProvinceInput()).to.eq('province', 'Expected Province value to be equals to province');
    expect(await exceptionOrderUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
    expect(await exceptionOrderUpdatePage.getDistrictInput()).to.eq('district', 'Expected District value to be equals to district');
    expect(await exceptionOrderUpdatePage.getLongitudeInput()).to.eq('5', 'Expected longitude value to be equals to 5');
    expect(await exceptionOrderUpdatePage.getLatitudeInput()).to.eq('5', 'Expected latitude value to be equals to 5');
    expect(await exceptionOrderUpdatePage.getAddressInput()).to.eq('address', 'Expected Address value to be equals to address');
    expect(await exceptionOrderUpdatePage.getPackingFeeInput()).to.eq('5', 'Expected packingFee value to be equals to 5');
    expect(await exceptionOrderUpdatePage.getDiningDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected diningDate value to be equals to 2000-12-31'
    );
    expect(await exceptionOrderUpdatePage.getRemarkInput()).to.eq('remark', 'Expected Remark value to be equals to remark');
    expect(await exceptionOrderUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await exceptionOrderUpdatePage.getPaidDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected paidDate value to be equals to 2000-12-31'
    );
    expect(await exceptionOrderUpdatePage.getExceptionDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected exceptionDate value to be equals to 2000-12-31'
    );
    expect(await exceptionOrderUpdatePage.getHandlerInput()).to.eq('handler', 'Expected Handler value to be equals to handler');
    expect(await exceptionOrderUpdatePage.getHandledDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected handledDate value to be equals to 2000-12-31'
    );
    expect(await exceptionOrderUpdatePage.getHandledDescInput()).to.eq(
      'handledDesc',
      'Expected HandledDesc value to be equals to handledDesc'
    );

    await exceptionOrderUpdatePage.save();
    expect(await exceptionOrderUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await exceptionOrderComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last ExceptionOrder', async () => {
    const nbButtonsBeforeDelete = await exceptionOrderComponentsPage.countDeleteButtons();
    await exceptionOrderComponentsPage.clickOnLastDeleteButton();

    exceptionOrderDeleteDialog = new ExceptionOrderDeleteDialog();
    expect(await exceptionOrderDeleteDialog.getDialogTitle()).to.eq('jhrApp.exceptionOrder.delete.question');
    await exceptionOrderDeleteDialog.clickOnConfirmButton();

    expect(await exceptionOrderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
