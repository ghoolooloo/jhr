import { element, by, ElementFinder } from 'protractor';

export class WriteOffCardComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-write-off-card div table .btn-danger'));
  title = element.all(by.css('jhi-write-off-card div h2#page-heading span')).first();
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

export class WriteOffCardUpdatePage {
  pageTitle = element(by.id('jhi-write-off-card-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  codeInput = element(by.id('field_code'));
  cardSNInput = element(by.id('field_cardSN'));
  cardTypeSelect = element(by.id('field_cardType'));
  titleInput = element(by.id('field_title'));
  thumbnailInput = element(by.id('field_thumbnail'));
  detailsInput = element(by.id('field_details'));
  validPeriodBeginAtInput = element(by.id('field_validPeriodBeginAt'));
  validPeriodEndAtInput = element(by.id('field_validPeriodEndAt'));
  canUseWithOtherCardInput = element(by.id('field_canUseWithOtherCard'));
  acceptCategoriesInput = element(by.id('field_acceptCategories'));
  acceptShopsInput = element(by.id('field_acceptShops'));
  leastCostInput = element(by.id('field_leastCost'));
  reduceCostInput = element(by.id('field_reduceCost'));
  discountInput = element(by.id('field_discount'));
  giftInput = element(by.id('field_gift'));
  giftQuantityInput = element(by.id('field_giftQuantity'));
  receivedByInput = element(by.id('field_receivedBy'));
  receivedDateInput = element(by.id('field_receivedDate'));
  orderSNInput = element(by.id('field_orderSN'));
  statusSelect = element(by.id('field_status'));
  writeOffDateInput = element(by.id('field_writeOffDate'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setCodeInput(code: string): Promise<void> {
    await this.codeInput.sendKeys(code);
  }

  async getCodeInput(): Promise<string> {
    return await this.codeInput.getAttribute('value');
  }

  async setCardSNInput(cardSN: string): Promise<void> {
    await this.cardSNInput.sendKeys(cardSN);
  }

  async getCardSNInput(): Promise<string> {
    return await this.cardSNInput.getAttribute('value');
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

  async setReceivedByInput(receivedBy: string): Promise<void> {
    await this.receivedByInput.sendKeys(receivedBy);
  }

  async getReceivedByInput(): Promise<string> {
    return await this.receivedByInput.getAttribute('value');
  }

  async setReceivedDateInput(receivedDate: string): Promise<void> {
    await this.receivedDateInput.sendKeys(receivedDate);
  }

  async getReceivedDateInput(): Promise<string> {
    return await this.receivedDateInput.getAttribute('value');
  }

  async setOrderSNInput(orderSN: string): Promise<void> {
    await this.orderSNInput.sendKeys(orderSN);
  }

  async getOrderSNInput(): Promise<string> {
    return await this.orderSNInput.getAttribute('value');
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

  async setWriteOffDateInput(writeOffDate: string): Promise<void> {
    await this.writeOffDateInput.sendKeys(writeOffDate);
  }

  async getWriteOffDateInput(): Promise<string> {
    return await this.writeOffDateInput.getAttribute('value');
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

export class WriteOffCardDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-writeOffCard-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-writeOffCard'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
