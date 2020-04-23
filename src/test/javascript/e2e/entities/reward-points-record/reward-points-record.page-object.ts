import { element, by, ElementFinder } from 'protractor';

export class RewardPointsRecordComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-reward-points-record div table .btn-danger'));
  title = element.all(by.css('jhi-reward-points-record div h2#page-heading span')).first();
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

export class RewardPointsRecordUpdatePage {
  pageTitle = element(by.id('jhi-reward-points-record-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  amountInput = element(by.id('field_amount'));
  remarkInput = element(by.id('field_remark'));
  createdDateInput = element(by.id('field_createdDate'));

  rewardPointsSelect = element(by.id('field_rewardPoints'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setAmountInput(amount: string): Promise<void> {
    await this.amountInput.sendKeys(amount);
  }

  async getAmountInput(): Promise<string> {
    return await this.amountInput.getAttribute('value');
  }

  async setRemarkInput(remark: string): Promise<void> {
    await this.remarkInput.sendKeys(remark);
  }

  async getRemarkInput(): Promise<string> {
    return await this.remarkInput.getAttribute('value');
  }

  async setCreatedDateInput(createdDate: string): Promise<void> {
    await this.createdDateInput.sendKeys(createdDate);
  }

  async getCreatedDateInput(): Promise<string> {
    return await this.createdDateInput.getAttribute('value');
  }

  async rewardPointsSelectLastOption(): Promise<void> {
    await this.rewardPointsSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async rewardPointsSelectOption(option: string): Promise<void> {
    await this.rewardPointsSelect.sendKeys(option);
  }

  getRewardPointsSelect(): ElementFinder {
    return this.rewardPointsSelect;
  }

  async getRewardPointsSelectedOption(): Promise<string> {
    return await this.rewardPointsSelect.element(by.css('option:checked')).getText();
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

export class RewardPointsRecordDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-rewardPointsRecord-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-rewardPointsRecord'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
