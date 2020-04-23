import { element, by, ElementFinder } from 'protractor';

export class FinalCardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-final-card div table .btn-danger'));
  title = element.all(by.css('jhi-final-card div h2#page-heading span')).first();
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

export class FinalCardUpdatePage {
  pageTitle = element(by.id('jhi-final-card-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  snInput = element(by.id('field_sn'));
  cardTypeSelect = element(by.id('field_cardType'));
  titleInput = element(by.id('field_title'));
  thumbnailInput = element(by.id('field_thumbnail'));
  detailsInput = element(by.id('field_details'));
  validPeriodBeginAtInput = element(by.id('field_validPeriodBeginAt'));
  validPeriodEndAtInput = element(by.id('field_validPeriodEndAt'));
  quantityInput = element(by.id('field_quantity'));
  receivedQuantityInput = element(by.id('field_receivedQuantity'));
  canUseWithOtherCardInput = element(by.id('field_canUseWithOtherCard'));
  acceptCategoriesInput = element(by.id('field_acceptCategories'));
  acceptShopsInput = element(by.id('field_acceptShops'));
  leastCostInput = element(by.id('field_leastCost'));
  reduceCostInput = element(by.id('field_reduceCost'));
  discountInput = element(by.id('field_discount'));
  giftInput = element(by.id('field_gift'));
  giftQuantityInput = element(by.id('field_giftQuantity'));
  statusSelect = element(by.id('field_status'));
  createdByInput = element(by.id('field_createdBy'));
  createdDateInput = element(by.id('field_createdDate'));
  deliveriedByInput = element(by.id('field_deliveriedBy'));
  deliveriedDateInput = element(by.id('field_deliveriedDate'));
  discontinueByInput = element(by.id('field_discontinueBy'));
  discontinueDateInput = element(by.id('field_discontinueDate'));
  lastModifiedDateInput = element(by.id('field_lastModifiedDate'));
  lastModifiedByInput = element(by.id('field_lastModifiedBy'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setSnInput(sn: string): Promise<void> {
    await this.snInput.sendKeys(sn);
  }

  async getSnInput(): Promise<string> {
    return await this.snInput.getAttribute('value');
  }

  async setCardTypeSelect(cardType: string): Promise<void> {
    await this.cardTypeSelect.sendKeys(cardType);
  }

  async getCardTypeSelect(): Promise<string> {
    return await this.cardTypeSelect.element(by.css('option:checked')).getText();
  }

  async cardTypeSelectLastOption(): Promise<void> {
    await this.cardTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setTitleInput(title: string): Promise<void> {
    await this.titleInput.sendKeys(title);
  }

  async getTitleInput(): Promise<string> {
    return await this.titleInput.getAttribute('value');
  }

  async setThumbnailInput(thumbnail: string): Promise<void> {
    await this.thumbnailInput.sendKeys(thumbnail);
  }

  async getThumbnailInput(): Promise<string> {
    return await this.thumbnailInput.getAttribute('value');
  }

  async setDetailsInput(details: string): Promise<void> {
    await this.detailsInput.sendKeys(details);
  }

  async getDetailsInput(): Promise<string> {
    return await this.detailsInput.getAttribute('value');
  }

  async setValidPeriodBeginAtInput(validPeriodBeginAt: string): Promise<void> {
    await this.validPeriodBeginAtInput.sendKeys(validPeriodBeginAt);
  }

  async getValidPeriodBeginAtInput(): Promise<string> {
    return await this.validPeriodBeginAtInput.getAttribute('value');
  }

  async setValidPeriodEndAtInput(validPeriodEndAt: string): Promise<void> {
    await this.validPeriodEndAtInput.sendKeys(validPeriodEndAt);
  }

  async getValidPeriodEndAtInput(): Promise<string> {
    return await this.validPeriodEndAtInput.getAttribute('value');
  }

  async setQuantityInput(quantity: string): Promise<void> {
    await this.quantityInput.sendKeys(quantity);
  }

  async getQuantityInput(): Promise<string> {
    return await this.quantityInput.getAttribute('value');
  }

  async setReceivedQuantityInput(receivedQuantity: string): Promise<void> {
    await this.receivedQuantityInput.sendKeys(receivedQuantity);
  }

  async getReceivedQuantityInput(): Promise<string> {
    return await this.receivedQuantityInput.getAttribute('value');
  }

  getCanUseWithOtherCardInput(): ElementFinder {
    return this.canUseWithOtherCardInput;
  }

  async setAcceptCategoriesInput(acceptCategories: string): Promise<void> {
    await this.acceptCategoriesInput.sendKeys(acceptCategories);
  }

  async getAcceptCategoriesInput(): Promise<string> {
    return await this.acceptCategoriesInput.getAttribute('value');
  }

  async setAcceptShopsInput(acceptShops: string): Promise<void> {
    await this.acceptShopsInput.sendKeys(acceptShops);
  }

  async getAcceptShopsInput(): Promise<string> {
    return await this.acceptShopsInput.getAttribute('value');
  }

  async setLeastCostInput(leastCost: string): Promise<void> {
    await this.leastCostInput.sendKeys(leastCost);
  }

  async getLeastCostInput(): Promise<string> {
    return await this.leastCostInput.getAttribute('value');
  }

  async setReduceCostInput(reduceCost: string): Promise<void> {
    await this.reduceCostInput.sendKeys(reduceCost);
  }

  async getReduceCostInput(): Promise<string> {
    return await this.reduceCostInput.getAttribute('value');
  }

  async setDiscountInput(discount: string): Promise<void> {
    await this.discountInput.sendKeys(discount);
  }

  async getDiscountInput(): Promise<string> {
    return await this.discountInput.getAttribute('value');
  }

  async setGiftInput(gift: string): Promise<void> {
    await this.giftInput.sendKeys(gift);
  }

  async getGiftInput(): Promise<string> {
    return await this.giftInput.getAttribute('value');
  }

  async setGiftQuantityInput(giftQuantity: string): Promise<void> {
    await this.giftQuantityInput.sendKeys(giftQuantity);
  }

  async getGiftQuantityInput(): Promise<string> {
    return await this.giftQuantityInput.getAttribute('value');
  }

  async setStatusSelect(status: string): Promise<void> {
    await this.statusSelect.sendKeys(status);
  }

  async getStatusSelect(): Promise<string> {
    return await this.statusSelect.element(by.css('option:checked')).getText();
  }

  async statusSelectLastOption(): Promise<void> {
    await this.statusSelect
      .all(by.tagName('option'))
      .last()
      .click();
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

  async setDeliveriedByInput(deliveriedBy: string): Promise<void> {
    await this.deliveriedByInput.sendKeys(deliveriedBy);
  }

  async getDeliveriedByInput(): Promise<string> {
    return await this.deliveriedByInput.getAttribute('value');
  }

  async setDeliveriedDateInput(deliveriedDate: string): Promise<void> {
    await this.deliveriedDateInput.sendKeys(deliveriedDate);
  }

  async getDeliveriedDateInput(): Promise<string> {
    return await this.deliveriedDateInput.getAttribute('value');
  }

  async setDiscontinueByInput(discontinueBy: string): Promise<void> {
    await this.discontinueByInput.sendKeys(discontinueBy);
  }

  async getDiscontinueByInput(): Promise<string> {
    return await this.discontinueByInput.getAttribute('value');
  }

  async setDiscontinueDateInput(discontinueDate: string): Promise<void> {
    await this.discontinueDateInput.sendKeys(discontinueDate);
  }

  async getDiscontinueDateInput(): Promise<string> {
    return await this.discontinueDateInput.getAttribute('value');
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

export class FinalCardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-finalCard-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-finalCard'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
