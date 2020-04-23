import { element, by, ElementFinder } from 'protractor';

export class FoodSalesReportComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-food-sales-report div table .btn-danger'));
  title = element.all(by.css('jhi-food-sales-report div h2#page-heading span')).first();
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

export class FoodSalesReportUpdatePage {
  pageTitle = element(by.id('jhi-food-sales-report-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  dayInput = element(by.id('field_day'));
  shopSNInput = element(by.id('field_shopSN'));
  shopNameInput = element(by.id('field_shopName'));
  categorySNInput = element(by.id('field_categorySN'));
  categoryNameInput = element(by.id('field_categoryName'));
  foodSNInput = element(by.id('field_foodSN'));
  foodNameInput = element(by.id('field_foodName'));
  costInput = element(by.id('field_cost'));
  priceInput = element(by.id('field_price'));
  originalPriceInput = element(by.id('field_originalPrice'));
  salesQuantityInput = element(by.id('field_salesQuantity'));
  salesAmountInput = element(by.id('field_salesAmount'));
  refundOrdersInput = element(by.id('field_refundOrders'));
  refundAmountInput = element(by.id('field_refundAmount'));
  grossProfitInput = element(by.id('field_grossProfit'));
  initQuantityInput = element(by.id('field_initQuantity'));
  remainderInput = element(by.id('field_remainder'));

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

  async setCostInput(cost: string): Promise<void> {
    await this.costInput.sendKeys(cost);
  }

  async getCostInput(): Promise<string> {
    return await this.costInput.getAttribute('value');
  }

  async setPriceInput(price: string): Promise<void> {
    await this.priceInput.sendKeys(price);
  }

  async getPriceInput(): Promise<string> {
    return await this.priceInput.getAttribute('value');
  }

  async setOriginalPriceInput(originalPrice: string): Promise<void> {
    await this.originalPriceInput.sendKeys(originalPrice);
  }

  async getOriginalPriceInput(): Promise<string> {
    return await this.originalPriceInput.getAttribute('value');
  }

  async setSalesQuantityInput(salesQuantity: string): Promise<void> {
    await this.salesQuantityInput.sendKeys(salesQuantity);
  }

  async getSalesQuantityInput(): Promise<string> {
    return await this.salesQuantityInput.getAttribute('value');
  }

  async setSalesAmountInput(salesAmount: string): Promise<void> {
    await this.salesAmountInput.sendKeys(salesAmount);
  }

  async getSalesAmountInput(): Promise<string> {
    return await this.salesAmountInput.getAttribute('value');
  }

  async setRefundOrdersInput(refundOrders: string): Promise<void> {
    await this.refundOrdersInput.sendKeys(refundOrders);
  }

  async getRefundOrdersInput(): Promise<string> {
    return await this.refundOrdersInput.getAttribute('value');
  }

  async setRefundAmountInput(refundAmount: string): Promise<void> {
    await this.refundAmountInput.sendKeys(refundAmount);
  }

  async getRefundAmountInput(): Promise<string> {
    return await this.refundAmountInput.getAttribute('value');
  }

  async setGrossProfitInput(grossProfit: string): Promise<void> {
    await this.grossProfitInput.sendKeys(grossProfit);
  }

  async getGrossProfitInput(): Promise<string> {
    return await this.grossProfitInput.getAttribute('value');
  }

  async setInitQuantityInput(initQuantity: string): Promise<void> {
    await this.initQuantityInput.sendKeys(initQuantity);
  }

  async getInitQuantityInput(): Promise<string> {
    return await this.initQuantityInput.getAttribute('value');
  }

  async setRemainderInput(remainder: string): Promise<void> {
    await this.remainderInput.sendKeys(remainder);
  }

  async getRemainderInput(): Promise<string> {
    return await this.remainderInput.getAttribute('value');
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

export class FoodSalesReportDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-foodSalesReport-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-foodSalesReport'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
