import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrderComponentsPage, OrderDeleteDialog, OrderUpdatePage } from './order.page-object';

const expect = chai.expect;

describe('Order e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let orderComponentsPage: OrderComponentsPage;
  let orderUpdatePage: OrderUpdatePage;
  let orderDeleteDialog: OrderDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Orders', async () => {
    await navBarPage.goToEntity('order');
    orderComponentsPage = new OrderComponentsPage();
    await browser.wait(ec.visibilityOf(orderComponentsPage.title), 5000);
    expect(await orderComponentsPage.getTitle()).to.eq('jhrApp.order.home.title');
    await browser.wait(ec.or(ec.visibilityOf(orderComponentsPage.entities), ec.visibilityOf(orderComponentsPage.noResult)), 1000);
  });

  it('should load create Order page', async () => {
    await orderComponentsPage.clickOnCreateButton();
    orderUpdatePage = new OrderUpdatePage();
    expect(await orderUpdatePage.getPageTitle()).to.eq('jhrApp.order.home.createOrEditLabel');
    await orderUpdatePage.cancel();
  });

  it('should create and save Orders', async () => {
    const nbButtonsBeforeCreate = await orderComponentsPage.countDeleteButtons();

    await orderComponentsPage.clickOnCreateButton();

    await promise.all([
      orderUpdatePage.setSnInput('sn'),
      orderUpdatePage.setMemberNameInput('memberName'),
      orderUpdatePage.setMemberSNInput('memberSN'),
      orderUpdatePage.orderTypeSelectLastOption(),
      orderUpdatePage.setShopSNInput('shopSN'),
      orderUpdatePage.setShopNameInput('shopName'),
      orderUpdatePage.setPriceTotalInput('5'),
      orderUpdatePage.setCardReduceInput('5'),
      orderUpdatePage.setCardsInput('cards'),
      orderUpdatePage.setRewardPointsReduceInput('5'),
      orderUpdatePage.setPaymentTotalInput('5'),
      orderUpdatePage.distributionPlatformSelectLastOption(),
      orderUpdatePage.setDeliveryNoInput('deliveryNo'),
      orderUpdatePage.setDeliveryFeeInput('5'),
      orderUpdatePage.setContactInput('contact'),
      orderUpdatePage.sexSelectLastOption(),
      orderUpdatePage.setMobileInput('mobile'),
      orderUpdatePage.setCountryInput('country'),
      orderUpdatePage.setProvinceInput('province'),
      orderUpdatePage.setCityInput('city'),
      orderUpdatePage.setDistrictInput('district'),
      orderUpdatePage.offsetTypeSelectLastOption(),
      orderUpdatePage.setLongitudeInput('5'),
      orderUpdatePage.setLatitudeInput('5'),
      orderUpdatePage.setAddressInput('address'),
      orderUpdatePage.setPackingFeeInput('5'),
      orderUpdatePage.paymentModeSelectLastOption(),
      orderUpdatePage.setDiningDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderUpdatePage.setRemarkInput('remark'),
      orderUpdatePage.statusSelectLastOption(),
      orderUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderUpdatePage.setPaidDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      orderUpdatePage.setExpiredDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM')
    ]);

    expect(await orderUpdatePage.getSnInput()).to.eq('sn', 'Expected Sn value to be equals to sn');
    expect(await orderUpdatePage.getMemberNameInput()).to.eq('memberName', 'Expected MemberName value to be equals to memberName');
    expect(await orderUpdatePage.getMemberSNInput()).to.eq('memberSN', 'Expected MemberSN value to be equals to memberSN');
    expect(await orderUpdatePage.getShopSNInput()).to.eq('shopSN', 'Expected ShopSN value to be equals to shopSN');
    expect(await orderUpdatePage.getShopNameInput()).to.eq('shopName', 'Expected ShopName value to be equals to shopName');
    expect(await orderUpdatePage.getPriceTotalInput()).to.eq('5', 'Expected priceTotal value to be equals to 5');
    expect(await orderUpdatePage.getCardReduceInput()).to.eq('5', 'Expected cardReduce value to be equals to 5');
    expect(await orderUpdatePage.getCardsInput()).to.eq('cards', 'Expected Cards value to be equals to cards');
    expect(await orderUpdatePage.getRewardPointsReduceInput()).to.eq('5', 'Expected rewardPointsReduce value to be equals to 5');
    expect(await orderUpdatePage.getPaymentTotalInput()).to.eq('5', 'Expected paymentTotal value to be equals to 5');
    expect(await orderUpdatePage.getDeliveryNoInput()).to.eq('deliveryNo', 'Expected DeliveryNo value to be equals to deliveryNo');
    expect(await orderUpdatePage.getDeliveryFeeInput()).to.eq('5', 'Expected deliveryFee value to be equals to 5');
    expect(await orderUpdatePage.getContactInput()).to.eq('contact', 'Expected Contact value to be equals to contact');
    expect(await orderUpdatePage.getMobileInput()).to.eq('mobile', 'Expected Mobile value to be equals to mobile');
    expect(await orderUpdatePage.getCountryInput()).to.eq('country', 'Expected Country value to be equals to country');
    expect(await orderUpdatePage.getProvinceInput()).to.eq('province', 'Expected Province value to be equals to province');
    expect(await orderUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
    expect(await orderUpdatePage.getDistrictInput()).to.eq('district', 'Expected District value to be equals to district');
    expect(await orderUpdatePage.getLongitudeInput()).to.eq('5', 'Expected longitude value to be equals to 5');
    expect(await orderUpdatePage.getLatitudeInput()).to.eq('5', 'Expected latitude value to be equals to 5');
    expect(await orderUpdatePage.getAddressInput()).to.eq('address', 'Expected Address value to be equals to address');
    expect(await orderUpdatePage.getPackingFeeInput()).to.eq('5', 'Expected packingFee value to be equals to 5');
    expect(await orderUpdatePage.getDiningDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected diningDate value to be equals to 2000-12-31'
    );
    expect(await orderUpdatePage.getRemarkInput()).to.eq('remark', 'Expected Remark value to be equals to remark');
    expect(await orderUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await orderUpdatePage.getPaidDateInput()).to.contain('2001-01-01T02:30', 'Expected paidDate value to be equals to 2000-12-31');
    expect(await orderUpdatePage.getExpiredDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected expiredDate value to be equals to 2000-12-31'
    );

    await orderUpdatePage.save();
    expect(await orderUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await orderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Order', async () => {
    const nbButtonsBeforeDelete = await orderComponentsPage.countDeleteButtons();
    await orderComponentsPage.clickOnLastDeleteButton();

    orderDeleteDialog = new OrderDeleteDialog();
    expect(await orderDeleteDialog.getDialogTitle()).to.eq('jhrApp.order.delete.question');
    await orderDeleteDialog.clickOnConfirmButton();

    expect(await orderComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
