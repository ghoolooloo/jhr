import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { OrderItemComponentsPage, OrderItemDeleteDialog, OrderItemUpdatePage } from './order-item.page-object';

const expect = chai.expect;

describe('OrderItem e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let orderItemComponentsPage: OrderItemComponentsPage;
  let orderItemUpdatePage: OrderItemUpdatePage;
  let orderItemDeleteDialog: OrderItemDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load OrderItems', async () => {
    await navBarPage.goToEntity('order-item');
    orderItemComponentsPage = new OrderItemComponentsPage();
    await browser.wait(ec.visibilityOf(orderItemComponentsPage.title), 5000);
    expect(await orderItemComponentsPage.getTitle()).to.eq('jhrApp.orderItem.home.title');
    await browser.wait(ec.or(ec.visibilityOf(orderItemComponentsPage.entities), ec.visibilityOf(orderItemComponentsPage.noResult)), 1000);
  });

  it('should load create OrderItem page', async () => {
    await orderItemComponentsPage.clickOnCreateButton();
    orderItemUpdatePage = new OrderItemUpdatePage();
    expect(await orderItemUpdatePage.getPageTitle()).to.eq('jhrApp.orderItem.home.createOrEditLabel');
    await orderItemUpdatePage.cancel();
  });

  it('should create and save OrderItems', async () => {
    const nbButtonsBeforeCreate = await orderItemComponentsPage.countDeleteButtons();

    await orderItemComponentsPage.clickOnCreateButton();

    await promise.all([
      orderItemUpdatePage.setOrderSNInput('orderSN'),
      orderItemUpdatePage.setFoodCategoryNameInput('foodCategoryName'),
      orderItemUpdatePage.setFoodCategorySNInput('foodCategorySN'),
      orderItemUpdatePage.setFoodNameInput('foodName'),
      orderItemUpdatePage.setFoodSNInput('foodSN'),
      orderItemUpdatePage.setFoodThumbnailInput('foodThumbnail'),
      orderItemUpdatePage.setFoodPictureInput('foodPicture'),
      orderItemUpdatePage.setPriceInput('5'),
      orderItemUpdatePage.setOriginalPriceInput('5'),
      orderItemUpdatePage.setCostInput('5'),
      orderItemUpdatePage.setPackingFeeInput('5'),
      orderItemUpdatePage.setDescInput('desc'),
      orderItemUpdatePage.setQuantityInput('5'),
      orderItemUpdatePage.setCouponInput('coupon')
    ]);

    expect(await orderItemUpdatePage.getOrderSNInput()).to.eq('orderSN', 'Expected OrderSN value to be equals to orderSN');
    expect(await orderItemUpdatePage.getFoodCategoryNameInput()).to.eq(
      'foodCategoryName',
      'Expected FoodCategoryName value to be equals to foodCategoryName'
    );
    expect(await orderItemUpdatePage.getFoodCategorySNInput()).to.eq(
      'foodCategorySN',
      'Expected FoodCategorySN value to be equals to foodCategorySN'
    );
    expect(await orderItemUpdatePage.getFoodNameInput()).to.eq('foodName', 'Expected FoodName value to be equals to foodName');
    expect(await orderItemUpdatePage.getFoodSNInput()).to.eq('foodSN', 'Expected FoodSN value to be equals to foodSN');
    expect(await orderItemUpdatePage.getFoodThumbnailInput()).to.eq(
      'foodThumbnail',
      'Expected FoodThumbnail value to be equals to foodThumbnail'
    );
    expect(await orderItemUpdatePage.getFoodPictureInput()).to.eq('foodPicture', 'Expected FoodPicture value to be equals to foodPicture');
    expect(await orderItemUpdatePage.getPriceInput()).to.eq('5', 'Expected price value to be equals to 5');
    expect(await orderItemUpdatePage.getOriginalPriceInput()).to.eq('5', 'Expected originalPrice value to be equals to 5');
    expect(await orderItemUpdatePage.getCostInput()).to.eq('5', 'Expected cost value to be equals to 5');
    expect(await orderItemUpdatePage.getPackingFeeInput()).to.eq('5', 'Expected packingFee value to be equals to 5');
    expect(await orderItemUpdatePage.getDescInput()).to.eq('desc', 'Expected Desc value to be equals to desc');
    expect(await orderItemUpdatePage.getQuantityInput()).to.eq('5', 'Expected quantity value to be equals to 5');
    expect(await orderItemUpdatePage.getCouponInput()).to.eq('coupon', 'Expected Coupon value to be equals to coupon');

    await orderItemUpdatePage.save();
    expect(await orderItemUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await orderItemComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last OrderItem', async () => {
    const nbButtonsBeforeDelete = await orderItemComponentsPage.countDeleteButtons();
    await orderItemComponentsPage.clickOnLastDeleteButton();

    orderItemDeleteDialog = new OrderItemDeleteDialog();
    expect(await orderItemDeleteDialog.getDialogTitle()).to.eq('jhrApp.orderItem.delete.question');
    await orderItemDeleteDialog.clickOnConfirmButton();

    expect(await orderItemComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
