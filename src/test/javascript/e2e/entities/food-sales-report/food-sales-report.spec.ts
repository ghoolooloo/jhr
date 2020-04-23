import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { FoodSalesReportComponentsPage, FoodSalesReportDeleteDialog, FoodSalesReportUpdatePage } from './food-sales-report.page-object';

const expect = chai.expect;

describe('FoodSalesReport e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let foodSalesReportComponentsPage: FoodSalesReportComponentsPage;
  let foodSalesReportUpdatePage: FoodSalesReportUpdatePage;
  let foodSalesReportDeleteDialog: FoodSalesReportDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load FoodSalesReports', async () => {
    await navBarPage.goToEntity('food-sales-report');
    foodSalesReportComponentsPage = new FoodSalesReportComponentsPage();
    await browser.wait(ec.visibilityOf(foodSalesReportComponentsPage.title), 5000);
    expect(await foodSalesReportComponentsPage.getTitle()).to.eq('jhrApp.foodSalesReport.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(foodSalesReportComponentsPage.entities), ec.visibilityOf(foodSalesReportComponentsPage.noResult)),
      1000
    );
  });

  it('should load create FoodSalesReport page', async () => {
    await foodSalesReportComponentsPage.clickOnCreateButton();
    foodSalesReportUpdatePage = new FoodSalesReportUpdatePage();
    expect(await foodSalesReportUpdatePage.getPageTitle()).to.eq('jhrApp.foodSalesReport.home.createOrEditLabel');
    await foodSalesReportUpdatePage.cancel();
  });

  it('should create and save FoodSalesReports', async () => {
    const nbButtonsBeforeCreate = await foodSalesReportComponentsPage.countDeleteButtons();

    await foodSalesReportComponentsPage.clickOnCreateButton();

    await promise.all([
      foodSalesReportUpdatePage.setDayInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      foodSalesReportUpdatePage.setShopSNInput('shopSN'),
      foodSalesReportUpdatePage.setShopNameInput('shopName'),
      foodSalesReportUpdatePage.setCategorySNInput('categorySN'),
      foodSalesReportUpdatePage.setCategoryNameInput('categoryName'),
      foodSalesReportUpdatePage.setFoodSNInput('foodSN'),
      foodSalesReportUpdatePage.setFoodNameInput('foodName'),
      foodSalesReportUpdatePage.setCostInput('5'),
      foodSalesReportUpdatePage.setPriceInput('5'),
      foodSalesReportUpdatePage.setOriginalPriceInput('5'),
      foodSalesReportUpdatePage.setSalesQuantityInput('5'),
      foodSalesReportUpdatePage.setSalesAmountInput('5'),
      foodSalesReportUpdatePage.setRefundOrdersInput('5'),
      foodSalesReportUpdatePage.setRefundAmountInput('5'),
      foodSalesReportUpdatePage.setGrossProfitInput('5'),
      foodSalesReportUpdatePage.setInitQuantityInput('5'),
      foodSalesReportUpdatePage.setRemainderInput('5')
    ]);

    expect(await foodSalesReportUpdatePage.getDayInput()).to.contain('2001-01-01T02:30', 'Expected day value to be equals to 2000-12-31');
    expect(await foodSalesReportUpdatePage.getShopSNInput()).to.eq('shopSN', 'Expected ShopSN value to be equals to shopSN');
    expect(await foodSalesReportUpdatePage.getShopNameInput()).to.eq('shopName', 'Expected ShopName value to be equals to shopName');
    expect(await foodSalesReportUpdatePage.getCategorySNInput()).to.eq(
      'categorySN',
      'Expected CategorySN value to be equals to categorySN'
    );
    expect(await foodSalesReportUpdatePage.getCategoryNameInput()).to.eq(
      'categoryName',
      'Expected CategoryName value to be equals to categoryName'
    );
    expect(await foodSalesReportUpdatePage.getFoodSNInput()).to.eq('foodSN', 'Expected FoodSN value to be equals to foodSN');
    expect(await foodSalesReportUpdatePage.getFoodNameInput()).to.eq('foodName', 'Expected FoodName value to be equals to foodName');
    expect(await foodSalesReportUpdatePage.getCostInput()).to.eq('5', 'Expected cost value to be equals to 5');
    expect(await foodSalesReportUpdatePage.getPriceInput()).to.eq('5', 'Expected price value to be equals to 5');
    expect(await foodSalesReportUpdatePage.getOriginalPriceInput()).to.eq('5', 'Expected originalPrice value to be equals to 5');
    expect(await foodSalesReportUpdatePage.getSalesQuantityInput()).to.eq('5', 'Expected salesQuantity value to be equals to 5');
    expect(await foodSalesReportUpdatePage.getSalesAmountInput()).to.eq('5', 'Expected salesAmount value to be equals to 5');
    expect(await foodSalesReportUpdatePage.getRefundOrdersInput()).to.eq('5', 'Expected refundOrders value to be equals to 5');
    expect(await foodSalesReportUpdatePage.getRefundAmountInput()).to.eq('5', 'Expected refundAmount value to be equals to 5');
    expect(await foodSalesReportUpdatePage.getGrossProfitInput()).to.eq('5', 'Expected grossProfit value to be equals to 5');
    expect(await foodSalesReportUpdatePage.getInitQuantityInput()).to.eq('5', 'Expected initQuantity value to be equals to 5');
    expect(await foodSalesReportUpdatePage.getRemainderInput()).to.eq('5', 'Expected remainder value to be equals to 5');

    await foodSalesReportUpdatePage.save();
    expect(await foodSalesReportUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await foodSalesReportComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last FoodSalesReport', async () => {
    const nbButtonsBeforeDelete = await foodSalesReportComponentsPage.countDeleteButtons();
    await foodSalesReportComponentsPage.clickOnLastDeleteButton();

    foodSalesReportDeleteDialog = new FoodSalesReportDeleteDialog();
    expect(await foodSalesReportDeleteDialog.getDialogTitle()).to.eq('jhrApp.foodSalesReport.delete.question');
    await foodSalesReportDeleteDialog.clickOnConfirmButton();

    expect(await foodSalesReportComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
