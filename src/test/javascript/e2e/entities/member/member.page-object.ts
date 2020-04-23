import { element, by, ElementFinder } from 'protractor';

export class MemberComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-member div table .btn-danger'));
  title = element.all(by.css('jhi-member div h2#page-heading span')).first();
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

export class MemberUpdatePage {
  pageTitle = element(by.id('jhi-member-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  nameInput = element(by.id('field_name'));
  snInput = element(by.id('field_sn'));
  openIDInput = element(by.id('field_openID'));
  unionIDInput = element(by.id('field_unionID'));
  profilePictureInput = element(by.id('field_profilePicture'));
  sexSelect = element(by.id('field_sex'));
  countryInput = element(by.id('field_country'));
  provinceInput = element(by.id('field_province'));
  cityInput = element(by.id('field_city'));
  joinDateInput = element(by.id('field_joinDate'));
  lastLoginDateInput = element(by.id('field_lastLoginDate'));

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

  async setOpenIDInput(openID: string): Promise<void> {
    await this.openIDInput.sendKeys(openID);
  }

  async getOpenIDInput(): Promise<string> {
    return await this.openIDInput.getAttribute('value');
  }

  async setUnionIDInput(unionID: string): Promise<void> {
    await this.unionIDInput.sendKeys(unionID);
  }

  async getUnionIDInput(): Promise<string> {
    return await this.unionIDInput.getAttribute('value');
  }

  async setProfilePictureInput(profilePicture: string): Promise<void> {
    await this.profilePictureInput.sendKeys(profilePicture);
  }

  async getProfilePictureInput(): Promise<string> {
    return await this.profilePictureInput.getAttribute('value');
  }

  async setSexSelect(sex: string): Promise<void> {
    await this.sexSelect.sendKeys(sex);
  }

  async getSexSelect(): Promise<string> {
    return await this.sexSelect.element(by.css('option:checked')).getText();
  }

  async sexSelectLastOption(): Promise<void> {
    await this.sexSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setCountryInput(country: string): Promise<void> {
    await this.countryInput.sendKeys(country);
  }

  async getCountryInput(): Promise<string> {
    return await this.countryInput.getAttribute('value');
  }

  async setProvinceInput(province: string): Promise<void> {
    await this.provinceInput.sendKeys(province);
  }

  async getProvinceInput(): Promise<string> {
    return await this.provinceInput.getAttribute('value');
  }

  async setCityInput(city: string): Promise<void> {
    await this.cityInput.sendKeys(city);
  }

  async getCityInput(): Promise<string> {
    return await this.cityInput.getAttribute('value');
  }

  async setJoinDateInput(joinDate: string): Promise<void> {
    await this.joinDateInput.sendKeys(joinDate);
  }

  async getJoinDateInput(): Promise<string> {
    return await this.joinDateInput.getAttribute('value');
  }

  async setLastLoginDateInput(lastLoginDate: string): Promise<void> {
    await this.lastLoginDateInput.sendKeys(lastLoginDate);
  }

  async getLastLoginDateInput(): Promise<string> {
    return await this.lastLoginDateInput.getAttribute('value');
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

export class MemberDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-member-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-member'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
