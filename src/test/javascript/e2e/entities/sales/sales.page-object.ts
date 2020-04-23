import { element, by, ElementFinder } from 'protractor';

export class SalesComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-sales div table .btn-danger'));
  title = element.all(by.css('jhi-sales div h2#page-heading span')).first();
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

export class SalesUpdatePage {
  pageTitle = element(by.id('jhi-sales-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  dayInput = element(by.id('field_day'));
  shopSNInput = element(by.id('field_shopSN'));
  shopNameInput = element(by.id('field_shopName'));
  categorySNInput = element(by.id('field_categorySN'));
  categoryNameInput = element(by.id('field_categoryName'));
  foodSNInput = element(by.id('field_foodSN'));
  foodNameInput = element(by.id('field_foodName'));
  foodOriginalPriceInput = element(by.id('field_foodOriginalPrice'));
  initQuantityInput = element(by.id('field_initQuantity'));
  salesQuantityInput = element(by.id('field_salesQuantity'));
  lastModifiedDateInput = element(by.id('field_lastModifiedDate'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setDayInput(day: string): Promise<void> {
    await this.dayInput.sendKeys(day);
  }

  async getDayInput(): Promise<string> {
    return await this.dayInput.getAttribute('value');
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

  async setCategorySNInput(categorySN: string): Promise<void> {
    await this.categorySNInput.sendKeys(categorySN);
  }

  async getCategorySNInput(): Promise<string> {
    return await this.categorySNInput.getAttribute('value');
  }

  async setCategoryNameInput(categoryName: string): Promise<void> {
    await this.categoryNameInput.sendKeys(categoryName);
  }

  async getCategoryNameInput(): Promise<string> {
    return await this.categoryNameInput.getAttribute('value');
  }

  async setFoodSNInput(foodSN: string): Promise<void> {
    await this.foodSNInput.sendKeys(foodSN);
  }

  async getFoodSNInput(): Promise<string> {
    return await this.foodSNInput.getAttribute('value');
  }

  async setFoodNameInput(foodName: string): Promise<void> {
    await this.foodNameInput.sendKeys(foodName);
  }

  async getFoodNameInput(): Promise<string> {
    return await this.foodNameInput.getAttribute('value');
  }

  async setFoodOriginalPriceInput(foodOriginalPrice: string): Promise<void> {
    await this.foodOriginalPriceInput.sendKeys(foodOriginalPrice);
  }

  async getFoodOriginalPriceInput(): Promise<string> {
    return await this.foodOriginalPriceInput.getAttribute('value');
  }

  async setInitQuantityInput(initQuantity: string): Promise<void> {
    await this.initQuantityInput.sendKeys(initQuantity);
  }

  async getInitQuantityInput(): Promise<string> {
    return await this.initQuantityInput.getAttribute('value');
  }

  async setSalesQuantityInput(salesQuantity: string): Promise<void> {
    await this.salesQuantityInput.sendKeys(salesQuantity);
  }

  async getSalesQuantityInput(): Promise<string> {
    return await this.salesQuantityInput.getAttribute('value');
  }

  async setLastModifiedDateInput(lastModifiedDate: string): Promise<void> {
    await this.lastModifiedDateInput.sendKeys(lastModifiedDate);
  }

  async getLastModifiedDateInput(): Promise<string> {
    return await this.lastModifiedDateInput.getAttribute('value');
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

export class SalesDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-sales-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-sales'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
