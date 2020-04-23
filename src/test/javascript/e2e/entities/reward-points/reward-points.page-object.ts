import { element, by, ElementFinder } from 'protractor';

export class RewardPointsComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-reward-points div table .btn-danger'));
  title = element.all(by.css('jhi-reward-points div h2#page-heading span')).first();
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

export class RewardPointsUpdatePage {
  pageTitle = element(by.id('jhi-reward-points-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  memberNameInput = element(by.id('field_memberName'));
  memberSNInput = element(by.id('field_memberSN'));
  balanceInput = element(by.id('field_balance'));
  lastModifiedDateInput = element(by.id('field_lastModifiedDate'));

  async getPageTitle(): Promise<string> {
    return this.pageTitle.getAttribute('jhiTranslate');
  }

  async setMemberNameInput(memberName: string): Promise<void> {
    await this.memberNameInput.sendKeys(memberName);
  }

  async getMemberNameInput(): Promise<string> {
    return await this.memberNameInput.getAttribute('value');
  }

  async setMemberSNInput(memberSN: string): Promise<void> {
    await this.memberSNInput.sendKeys(memberSN);
  }

  async getMemberSNInput(): Promise<string> {
    return await this.memberSNInput.getAttribute('value');
  }

  async setBalanceInput(balance: string): Promise<void> {
    await this.balanceInput.sendKeys(balance);
  }

  async getBalanceInput(): Promise<string> {
    return await this.balanceInput.getAttribute('value');
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

export class RewardPointsDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-rewardPoints-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-rewardPoints'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
