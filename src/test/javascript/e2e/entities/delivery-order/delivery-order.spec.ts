import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DeliveryOrderComponentsPage, DeliveryOrderDeleteDialog, DeliveryOrderUpdatePage } from './delivery-order.page-object';

const expect = chai.expect;

describe('DeliveryOrder e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let deliveryOrderComponentsPage: DeliveryOrderComponentsPage;
  let deliveryOrderUpdatePage: DeliveryOrderUpdatePage;
  let deliveryOrderDeleteDialog: DeliveryOrderDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DeliveryOrders', async () => {
    await navBarPage.goToEntity('delivery-order');
    deliveryOrderComponentsPage = new DeliveryOrderComponentsPage();
    await browser.wait(ec.visibilityOf(deliveryOrderComponentsPage.title), 5000);
    expect(await deliveryOrderComponentsPage.getTitle()).to.eq('jhrApp.deliveryOrder.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(deliveryOrderComponentsPage.entities), ec.visibilityOf(deliveryOrderComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DeliveryOrder page', async () => {
    await deliveryOrderComponentsPage.clickOnCreateButton();
    deliveryOrderUpdatePage = new DeliveryOrderUpdatePage();
    expect(await deliveryOrderUpdatePage.getPageTitle()).to.eq('jhrApp.deliveryOrder.home.createOrEditLabel');
    await deliveryOrderUpdatePage.cancel();
  });

  it('should create and save DeliveryOrders', async () => {
    const nbButtonsBeforeCreate = await deliveryOrderComponentsPage.countDeleteButtons();

    await deliveryOrderComponentsPage.clickOnCreateButton();

    await promise.all([
      deliveryOrderUpdatePage.setSnInput('sn'),
      deliveryOrderUpdatePage.setMemberNameInput('memberName'),
      deliveryOrderUpdatePage.setMemberSNInput('memberSN'),
      deliveryOrderUpdatePage.orderTypeSelectLastOption(),
      deliveryOrderUpdatePage.setShopSNInput('shopSN'),
      deliveryOrderUpdatePage.setShopNameInput('shopName'),
      deliveryOrderUpdatePage.setPriceTotalInput('5'),
      deliveryOrderUpdatePage.setCardReduceInput('5'),
      deliveryOrderUpdatePage.setCardsInput('cards'),
      deliveryOrderUpdatePage.setRewardPointsReduceInput('5'),
      deliveryOrderUpdatePage.setPaymentTotalInput('5'),
      deliveryOrderUpdatePage.distributionPlatformSelectLastOption(),
      deliveryOrderUpdatePage.setDeliveryNoInput('deliveryNo'),
      deliveryOrderUpdatePage.setDeliveryStatusInput('5'),
      deliveryOrderUpdatePage.setDeliveryDescInput('deliveryDesc'),
      deliveryOrderUpdatePage.setDeliverierInput('deliverier'),
      deliveryOrderUpdatePage.setDeliverierMobileInput('deliverierMobile'),
      deliveryOrderUpdatePage.setDeliveryDeductFeeInput('5'),
      deliveryOrderUpdatePage.setDeliveryFeeInput('5'),
      deliveryOrderUpdatePage.setContactInput('contact'),
      deliveryOrderUpdatePage.sexSelectLastOption(),
      deliveryOrderUpdatePage.setMobileInput('mobile'),
      deliveryOrderUpdatePage.setCountryInput('country'),
      deliveryOrderUpdatePage.setProvinceInput('province'),
      deliveryOrderUpdatePage.setCityInput('city'),
      deliveryOrderUpdatePage.setDistrictInput('district'),
      deliveryOrderUpdatePage.offsetTypeSelectLastOption(),
      deliveryOrderUpdatePage.setLongitudeInput('5'),
      deliveryOrderUpdatePage.setLatitudeInput('5'),
      deliveryOrderUpdatePage.setAddressInput('address'),
      deliveryOrderUpdatePage.setPackingFeeInput('5'),
      deliveryOrderUpdatePage.paymentModeSelectLastOption(),
      deliveryOrderUpdatePage.setDiningDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deliveryOrderUpdatePage.setRemarkInput('remark'),
      deliveryOrderUpdatePage.statusSelectLastOption(),
      deliveryOrderUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deliveryOrderUpdatePage.setPaidDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deliveryOrderUpdatePage.setCompletedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      deliveryOrderUpdatePage.setLastModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM')
    ]);

    expect(await deliveryOrderUpdatePage.getSnInput()).to.eq('sn', 'Expected Sn value to be equals to sn');
    expect(await deliveryOrderUpdatePage.getMemberNameInput()).to.eq('memberName', 'Expected MemberName value to be equals to memberName');
    expect(await deliveryOrderUpdatePage.getMemberSNInput()).to.eq('memberSN', 'Expected MemberSN value to be equals to memberSN');
    expect(await deliveryOrderUpdatePage.getShopSNInput()).to.eq('shopSN', 'Expected ShopSN value to be equals to shopSN');
    expect(await deliveryOrderUpdatePage.getShopNameInput()).to.eq('shopName', 'Expected ShopName value to be equals to shopName');
    expect(await deliveryOrderUpdatePage.getPriceTotalInput()).to.eq('5', 'Expected priceTotal value to be equals to 5');
    expect(await deliveryOrderUpdatePage.getCardReduceInput()).to.eq('5', 'Expected cardReduce value to be equals to 5');
    expect(await deliveryOrderUpdatePage.getCardsInput()).to.eq('cards', 'Expected Cards value to be equals to cards');
    expect(await deliveryOrderUpdatePage.getRewardPointsReduceInput()).to.eq('5', 'Expected rewardPointsReduce value to be equals to 5');
    expect(await deliveryOrderUpdatePage.getPaymentTotalInput()).to.eq('5', 'Expected paymentTotal value to be equals to 5');
    expect(await deliveryOrderUpdatePage.getDeliveryNoInput()).to.eq('deliveryNo', 'Expected DeliveryNo value to be equals to deliveryNo');
    expect(await deliveryOrderUpdatePage.getDeliveryStatusInput()).to.eq('5', 'Expected deliveryStatus value to be equals to 5');
    expect(await deliveryOrderUpdatePage.getDeliveryDescInput()).to.eq(
      'deliveryDesc',
      'Expected DeliveryDesc value to be equals to deliveryDesc'
    );
    expect(await deliveryOrderUpdatePage.getDeliverierInput()).to.eq('deliverier', 'Expected Deliverier value to be equals to deliverier');
    expect(await deliveryOrderUpdatePage.getDeliverierMobileInput()).to.eq(
      'deliverierMobile',
      'Expected DeliverierMobile value to be equals to deliverierMobile'
    );
    expect(await deliveryOrderUpdatePage.getDeliveryDeductFeeInput()).to.eq('5', 'Expected deliveryDeductFee value to be equals to 5');
    expect(await deliveryOrderUpdatePage.getDeliveryFeeInput()).to.eq('5', 'Expected deliveryFee value to be equals to 5');
    expect(await deliveryOrderUpdatePage.getContactInput()).to.eq('contact', 'Expected Contact value to be equals to contact');
    expect(await deliveryOrderUpdatePage.getMobileInput()).to.eq('mobile', 'Expected Mobile value to be equals to mobile');
    expect(await deliveryOrderUpdatePage.getCountryInput()).to.eq('country', 'Expected Country value to be equals to country');
    expect(await deliveryOrderUpdatePage.getProvinceInput()).to.eq('province', 'Expected Province value to be equals to province');
    expect(await deliveryOrderUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
    expect(await deliveryOrderUpdatePage.getDistrictInput()).to.eq('district', 'Expected District value to be equals to district');
    expect(await deliveryOrderUpdatePage.getLongitudeInput()).to.eq('5', 'Expected longitude value to be equals to 5');
    expect(await deliveryOrderUpdatePage.getLatitudeInput()).to.eq('5', 'Expected latitude value to be equals to 5');
    expect(await deliveryOrderUpdatePage.getAddressInput()).to.eq('address', 'Expected Address value to be equals to address');
    expect(await deliveryOrderUpdatePage.getPackingFeeInput()).to.eq('5', 'Expected packingFee value to be equals to 5');
    expect(await deliveryOrderUpdatePage.getDiningDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected diningDate value to be equals to 2000-12-31'
    );
    expect(await deliveryOrderUpdatePage.getRemarkInput()).to.eq('remark', 'Expected Remark value to be equals to remark');
    expect(await deliveryOrderUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await deliveryOrderUpdatePage.getPaidDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected paidDate value to be equals to 2000-12-31'
    );
    expect(await deliveryOrderUpdatePage.getCompletedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected completedDate value to be equals to 2000-12-31'
    );
    expect(await deliveryOrderUpdatePage.getLastModifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastModifiedDate value to be equals to 2000-12-31'
    );

    await deliveryOrderUpdatePage.save();
    expect(await deliveryOrderUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await deliveryOrderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last DeliveryOrder', async () => {
    const nbButtonsBeforeDelete = await deliveryOrderComponentsPage.countDeleteButtons();
    await deliveryOrderComponentsPage.clickOnLastDeleteButton();

    deliveryOrderDeleteDialog = new DeliveryOrderDeleteDialog();
    expect(await deliveryOrderDeleteDialog.getDialogTitle()).to.eq('jhrApp.deliveryOrder.delete.question');
    await deliveryOrderDeleteDialog.clickOnConfirmButton();

    expect(await deliveryOrderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
