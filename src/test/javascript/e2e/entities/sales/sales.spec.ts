import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { SalesComponentsPage, SalesDeleteDialog, SalesUpdatePage } from './sales.page-object';

const expect = chai.expect;

describe('Sales e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let salesComponentsPage: SalesComponentsPage;
  let salesUpdatePage: SalesUpdatePage;
  let salesDeleteDialog: SalesDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Sales', async () => {
    await navBarPage.goToEntity('sales');
    salesComponentsPage = new SalesComponentsPage();
    await browser.wait(ec.visibilityOf(salesComponentsPage.title), 5000);
    expect(await salesComponentsPage.getTitle()).to.eq('jhrApp.sales.home.title');
    await browser.wait(ec.or(ec.visibilityOf(salesComponentsPage.entities), ec.visibilityOf(salesComponentsPage.noResult)), 1000);
  });

  it('should load create Sales page', async () => {
    await salesComponentsPage.clickOnCreateButton();
    salesUpdatePage = new SalesUpdatePage();
    expect(await salesUpdatePage.getPageTitle()).to.eq('jhrApp.sales.home.createOrEditLabel');
    await salesUpdatePage.cancel();
  });

  it('should create and save Sales', async () => {
    const nbButtonsBeforeCreate = await salesComponentsPage.countDeleteButtons();

    await salesComponentsPage.clickOnCreateButton();

    await promise.all([
      salesUpdatePage.setDayInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      salesUpdatePage.setShopSNInput('shopSN'),
      salesUpdatePage.setShopNameInput('shopName'),
      salesUpdatePage.setCategorySNInput('categorySN'),
      salesUpdatePage.setCategoryNameInput('categoryName'),
      salesUpdatePage.setFoodSNInput('foodSN'),
      salesUpdatePage.setFoodNameInput('foodName'),
      salesUpdatePage.setFoodOriginalPriceInput('5'),
      salesUpdatePage.setInitQuantityInput('5'),
      salesUpdatePage.setSalesQuantityInput('5'),
      salesUpdatePage.setLastModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM')
    ]);

    expect(await salesUpdatePage.getDayInput()).to.contain('2001-01-01T02:30', 'Expected day value to be equals to 2000-12-31');
    expect(await salesUpdatePage.getShopSNInput()).to.eq('shopSN', 'Expected ShopSN value to be equals to shopSN');
    expect(await salesUpdatePage.getShopNameInput()).to.eq('shopName', 'Expected ShopName value to be equals to shopName');
    expect(await salesUpdatePage.getCategorySNInput()).to.eq('categorySN', 'Expected CategorySN value to be equals to categorySN');
    expect(await salesUpdatePage.getCategoryNameInput()).to.eq('categoryName', 'Expected CategoryName value to be equals to categoryName');
    expect(await salesUpdatePage.getFoodSNInput()).to.eq('foodSN', 'Expected FoodSN value to be equals to foodSN');
    expect(await salesUpdatePage.getFoodNameInput()).to.eq('foodName', 'Expected FoodName value to be equals to foodName');
    expect(await salesUpdatePage.getFoodOriginalPriceInput()).to.eq('5', 'Expected foodOriginalPrice value to be equals to 5');
    expect(await salesUpdatePage.getInitQuantityInput()).to.eq('5', 'Expected initQuantity value to be equals to 5');
    expect(await salesUpdatePage.getSalesQuantityInput()).to.eq('5', 'Expected salesQuantity value to be equals to 5');
    expect(await salesUpdatePage.getLastModifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastModifiedDate value to be equals to 2000-12-31'
    );

    await salesUpdatePage.save();
    expect(await salesUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await salesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Sales', async () => {
    const nbButtonsBeforeDelete = await salesComponentsPage.countDeleteButtons();
    await salesComponentsPage.clickOnLastDeleteButton();

    salesDeleteDialog = new SalesDeleteDialog();
    expect(await salesDeleteDialog.getDialogTitle()).to.eq('jhrApp.sales.delete.question');
    await salesDeleteDialog.clickOnConfirmButton();

    expect(await salesComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
