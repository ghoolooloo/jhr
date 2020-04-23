import { element, by, ElementFinder } from 'protractor';

export class DeliveryAddressComponentsPage {
  createButton = element(by.id('jh-create-entity'));
  deleteButtons = element.all(by.css('jhi-delivery-address div table .btn-danger'));
  title = element.all(by.css('jhi-delivery-address div h2#page-heading span')).first();
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

export class DeliveryAddressUpdatePage {
  pageTitle = element(by.id('jhi-delivery-address-heading'));
  saveButton = element(by.id('save-entity'));
  cancelButton = element(by.id('cancel-save'));

  memberNameInput = element(by.id('field_memberName'));
  memberSNInput = element(by.id('field_memberSN'));
  contactInput = element(by.id('field_contact'));
  sexSelect = element(by.id('field_sex'));
  mobileInput = element(by.id('field_mobile'));
  countryInput = element(by.id('field_country'));
  provinceInput = element(by.id('field_province'));
  cityInput = element(by.id('field_city'));
  districtInput = element(by.id('field_district'));
  offsetTypeSelect = element(by.id('field_offsetType'));
  longitudeInput = element(by.id('field_longitude'));
  latitudeInput = element(by.id('field_latitude'));
  addressInput = element(by.id('field_address'));

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

  async setContactInput(contact: string): Promise<void> {
    await this.contactInput.sendKeys(contact);
  }

  async getContactInput(): Promise<string> {
    return await this.contactInput.getAttribute('value');
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

  async setMobileInput(mobile: string): Promise<void> {
    await this.mobileInput.sendKeys(mobile);
  }

  async getMobileInput(): Promise<string> {
    return await this.mobileInput.getAttribute('value');
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

  async setDistrictInput(district: string): Promise<void> {
    await this.districtInput.sendKeys(district);
  }

  async getDistrictInput(): Promise<string> {
    return await this.districtInput.getAttribute('value');
  }

  async setOffsetTypeSelect(offsetType: string): Promise<void> {
    await this.offsetTypeSelect.sendKeys(offsetType);
  }

  async getOffsetTypeSelect(): Promise<string> {
    return await this.offsetTypeSelect.element(by.css('option:checked')).getText();
  }

  async offsetTypeSelectLastOption(): Promise<void> {
    await this.offsetTypeSelect
      .all(by.tagName('option'))
      .last()
      .click();
  }

  async setLongitudeInput(longitude: string): Promise<void> {
    await this.longitudeInput.sendKeys(longitude);
  }

  async getLongitudeInput(): Promise<string> {
    return await this.longitudeInput.getAttribute('value');
  }

  async setLatitudeInput(latitude: string): Promise<void> {
    await this.latitudeInput.sendKeys(latitude);
  }

  async getLatitudeInput(): Promise<string> {
    return await this.latitudeInput.getAttribute('value');
  }

  async setAddressInput(address: string): Promise<void> {
    await this.addressInput.sendKeys(address);
  }

  async getAddressInput(): Promise<string> {
    return await this.addressInput.getAttribute('value');
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

export class DeliveryAddressDeleteDialog {
  private dialogTitle = element(by.id('jhi-delete-deliveryAddress-heading'));
  private confirmButton = element(by.id('jhi-confirm-delete-deliveryAddress'));

  async getDialogTitle(): Promise<string> {
    return this.dialogTitle.getAttribute('jhiTranslate');
  }

  async clickOnConfirmButton(): Promise<void> {
    await this.confirmButton.click();
  }
}
