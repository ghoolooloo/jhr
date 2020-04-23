import { element, by, ElementFinder } from 'protractor';

export class OrderItemComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-order-item div table .btn-danger'));
  title = element.all(by.css('jhi-order-item div h2#page-heading span')).first();
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

export class OrderItemUpdatePage {
  pageTitle = element(by.id('jhi-order-item-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  orderSNInput = element(by.id('field_orderSN'));
  foodCategoryNameInput = element(by.id('field_foodCategoryName'));
  foodCategorySNInput = element(by.id('field_foodCategorySN'));
  foodNameInput = element(by.id('field_foodName'));
  foodSNInput = element(by.id('field_foodSN'));
  foodThumbnailInput = element(by.id('field_foodThumbnail'));
  foodPictureInput = element(by.id('field_foodPicture'));
  priceInput = element(by.id('field_price'));
  originalPriceInput = element(by.id('field_originalPrice'));
  costInput = element(by.id('field_cost'));
  packingFeeInput = element(by.id('field_packingFee'));
  descInput = element(by.id('field_desc'));
  quantityInput = element(by.id('field_quantity'));
  couponInput = element(by.id('field_coupon'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setOrderSNInput(orderSN: string): Promise<void> {
    await this.orderSNInput.sendKeys(orderSN);
  }

  async getOrderSNInput(): Promise<string> {
    return await this.orderSNInput.getAttribute('value');
  }

  async setFoodCategoryNameInput(foodCategoryName: string): Promise<void> {
    await this.foodCategoryNameInput.sendKeys(foodCategoryName);
  }

  async getFoodCategoryNameInput(): Promise<string> {
    return await this.foodCategoryNameInput.getAttribute('value');
  }

  async setFoodCategorySNInput(foodCategorySN: string): Promise<void> {
    await this.foodCategorySNInput.sendKeys(foodCategorySN);
  }

  async getFoodCategorySNInput(): Promise<string> {
    return await this.foodCategorySNInput.getAttribute('value');
  }

  async setFoodNameInput(foodName: string): Promise<void> {
    await this.foodNameInput.sendKeys(foodName);
  }

  async getFoodNameInput(): Promise<string> {
    return await this.foodNameInput.getAttribute('value');
  }

  async setFoodSNInput(foodSN: string): Promise<void> {
    await this.foodSNInput.sendKeys(foodSN);
  }

  async getFoodSNInput(): Promise<string> {
    return await this.foodSNInput.getAttribute('value');
  }

  async setFoodThumbnailInput(foodThumbnail: string): Promise<void> {
    await this.foodThumbnailInput.sendKeys(foodThumbnail);
  }

  async getFoodThumbnailInput(): Promise<string> {
    return await this.foodThumbnailInput.getAttribute('value');
  }

  async setFoodPictureInput(foodPicture: string): Promise<void> {
    await this.foodPictureInput.sendKeys(foodPicture);
  }

  async getFoodPictureInput(): Promise<string> {
    return await this.foodPictureInput.getAttribute('value');
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

  async setCostInput(cost: string): Promise<void> {
    await this.costInput.sendKeys(cost);
  }

  async getCostInput(): Promise<string> {
    return await this.costInput.getAttribute('value');
  }

  async setPackingFeeInput(packingFee: string): Promise<void> {
    await this.packingFeeInput.sendKeys(packingFee);
  }

  async getPackingFeeInput(): Promise<string> {
    return await this.packingFeeInput.getAttribute('value');
  }

  async setDescInput(desc: string): Promise<void> {
    await this.descInput.sendKeys(desc);
  }

  async getDescInput(): Promise<string> {
    return await this.descInput.getAttribute('value');
  }

  async setQuantityInput(quantity: string): Promise<void> {
    await this.quantityInput.sendKeys(quantity);
  }

  async getQuantityInput(): Promise<string> {
    return await this.quantityInput.getAttribute('value');
  }

  async setCouponInput(coupon: string): Promise<void> {
    await this.couponInput.sendKeys(coupon);
  }

  async getCouponInput(): Promise<string> {
    return await this.couponInput.getAttribute('value');
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

export class OrderItemDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-orderItem-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-orderItem'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
