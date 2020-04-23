import { element, by, ElementFinder } from 'protractor';

export class FoodComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-food div table .btn-danger'));
  title = element.all(by.css('jhi-food div h2#page-heading span')).first();
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

export class FoodUpdatePage {
  pageTitle = element(by.id('jhi-food-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nameInput = element(by.id('field_name'));
  snInput = element(by.id('field_sn'));
  thumbnailInput = element(by.id('field_thumbnail'));
  pictureInput = element(by.id('field_picture'));
  priceInput = element(by.id('field_price'));
  originalPriceInput = element(by.id('field_originalPrice'));
  costInput = element(by.id('field_cost'));
  packingFeeInput = element(by.id('field_packingFee'));
  descInput = element(by.id('field_desc'));
  sortInput = element(by.id('field_sort'));
  feedingModeSelect = element(by.id('field_feedingMode'));
  disabledInput = element(by.id('field_disabled'));
  createdByInput = element(by.id('field_createdBy'));
  createdDateInput = element(by.id('field_createdDate'));
  lastModifiedDateInput = element(by.id('field_lastModifiedDate'));
  lastModifiedByInput = element(by.id('field_lastModifiedBy'));

  categorySelect = element(by.id('field_category'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setNameInput(name: string): Promise<void> {
    await this.nameInput.sendKeys(name);
  }

  async getNameInput(): Promise<string> {
    return await this.nameInput.getAttribute('value');
  }

  async setSnInput(sn: string): Promise<void> {
    await this.snInput.sendKeys(sn);
  }

  async getSnInput(): Promise<string> {
    return await this.snInput.getAttribute('value');
  }

  async setThumbnailInput(thumbnail: string): Promise<void> {
    await this.thumbnailInput.sendKeys(thumbnail);
  }

  async getThumbnailInput(): Promise<string> {
    return await this.thumbnailInput.getAttribute('value');
  }

  async setPictureInput(picture: string): Promise<void> {
    await this.pictureInput.sendKeys(picture);
  }

  async getPictureInput(): Promise<string> {
    return await this.pictureInput.getAttribute('value');
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

  async setSortInput(sort: string): Promise<void> {
    await this.sortInput.sendKeys(sort);
  }

  async getSortInput(): Promise<string> {
    return await this.sortInput.getAttribute('value');
  }

  async setFeedingModeSelect(feedingMode: string): Promise<void> {
    await this.feedingModeSelect.sendKeys(feedingMode);
  }

  async getFeedingModeSelect(): Promise<string> {
    return await this.feedingModeSelect.element(by.css('option:checked')).getText();
  }

  async feedingModeSelectLastOption(): Promise<void> {
    await this.feedingModeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  getDisabledInput(): ElementFinder {
    return this.disabledInput;
  }

  async setCreatedByInput(createdBy: string): Promise<void> {
    await this.createdByInput.sendKeys(createdBy);
  }

  async getCreatedByInput(): Promise<string> {
    return await this.createdByInput.getAttribute('value');
  }

  async setCreatedDateInput(createdDate: string): Promise<void> {
    await this.createdDateInput.sendKeys(createdDate);
  }

  async getCreatedDateInput(): Promise<string> {
    return await this.createdDateInput.getAttribute('value');
  }

  async setLastModifiedDateInput(lastModifiedDate: string): Promise<void> {
    await this.lastModifiedDateInput.sendKeys(lastModifiedDate);
  }

  async getLastModifiedDateInput(): Promise<string> {
    return await this.lastModifiedDateInput.getAttribute('value');
  }

  async setLastModifiedByInput(lastModifiedBy: string): Promise<void> {
    await this.lastModifiedByInput.sendKeys(lastModifiedBy);
  }

  async getLastModifiedByInput(): Promise<string> {
    return await this.lastModifiedByInput.getAttribute('value');
  }

  async categorySelectLastOption(): Promise<void> {
    await this.categorySelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async categorySelectOption(option: string): Promise<void> {
    await this.categorySelect.sendKeys(option);
  }

  getCategorySelect(): ElementFinder {
    return this.categorySelect;
  }

  async getCategorySelectedOption(): Promise<string> {
    return await this.categorySelect.element(by.css('option:checked')).getText();
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

export class FoodDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-food-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-food'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
