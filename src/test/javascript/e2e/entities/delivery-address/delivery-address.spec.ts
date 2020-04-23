import { browser, ExpectedConditions as ec, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { DeliveryAddressComponentsPage, DeliveryAddressDeleteDialog, DeliveryAddressUpdatePage } from './delivery-address.page-object';

const expect = chai.expect;

describe('DeliveryAddress e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let deliveryAddressComponentsPage: DeliveryAddressComponentsPage;
  let deliveryAddressUpdatePage: DeliveryAddressUpdatePage;
  let deliveryAddressDeleteDialog: DeliveryAddressDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load DeliveryAddresses', async () => {
    await navBarPage.goToEntity('delivery-address');
    deliveryAddressComponentsPage = new DeliveryAddressComponentsPage();
    await browser.wait(ec.visibilityOf(deliveryAddressComponentsPage.title), 5000);
    expect(await deliveryAddressComponentsPage.getTitle()).to.eq('jhrApp.deliveryAddress.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(deliveryAddressComponentsPage.entities), ec.visibilityOf(deliveryAddressComponentsPage.noResult)),
      1000
    );
  });

  it('should load create DeliveryAddress page', async () => {
    await deliveryAddressComponentsPage.clickOnCreateButton();
    deliveryAddressUpdatePage = new DeliveryAddressUpdatePage();
    expect(await deliveryAddressUpdatePage.getPageTitle()).to.eq('jhrApp.deliveryAddress.home.createOrEditLabel');
    await deliveryAddressUpdatePage.cancel();
  });

  it('should create and save DeliveryAddresses', async () => {
    const nbButtonsBeforeCreate = await deliveryAddressComponentsPage.countDeleteButtons();

    await deliveryAddressComponentsPage.clickOnCreateButton();

    await promise.all([
      deliveryAddressUpdatePage.setMemberNameInput('memberName'),
      deliveryAddressUpdatePage.setMemberSNInput('memberSN'),
      deliveryAddressUpdatePage.setContactInput('contact'),
      deliveryAddressUpdatePage.sexSelectLastOption(),
      deliveryAddressUpdatePage.setMobileInput('mobile'),
      deliveryAddressUpdatePage.setCountryInput('country'),
      deliveryAddressUpdatePage.setProvinceInput('province'),
      deliveryAddressUpdatePage.setCityInput('city'),
      deliveryAddressUpdatePage.setDistrictInput('district'),
      deliveryAddressUpdatePage.offsetTypeSelectLastOption(),
      deliveryAddressUpdatePage.setLongitudeInput('5'),
      deliveryAddressUpdatePage.setLatitudeInput('5'),
      deliveryAddressUpdatePage.setAddressInput('address')
    ]);

    expect(await deliveryAddressUpdatePage.getMemberNameInput()).to.eq(
      'memberName',
      'Expected MemberName value to be equals to memberName'
    );
    expect(await deliveryAddressUpdatePage.getMemberSNInput()).to.eq('memberSN', 'Expected MemberSN value to be equals to memberSN');
    expect(await deliveryAddressUpdatePage.getContactInput()).to.eq('contact', 'Expected Contact value to be equals to contact');
    expect(await deliveryAddressUpdatePage.getMobileInput()).to.eq('mobile', 'Expected Mobile value to be equals to mobile');
    expect(await deliveryAddressUpdatePage.getCountryInput()).to.eq('country', 'Expected Country value to be equals to country');
    expect(await deliveryAddressUpdatePage.getProvinceInput()).to.eq('province', 'Expected Province value to be equals to province');
    expect(await deliveryAddressUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
    expect(await deliveryAddressUpdatePage.getDistrictInput()).to.eq('district', 'Expected District value to be equals to district');
    expect(await deliveryAddressUpdatePage.getLongitudeInput()).to.eq('5', 'Expected longitude value to be equals to 5');
    expect(await deliveryAddressUpdatePage.getLatitudeInput()).to.eq('5', 'Expected latitude value to be equals to 5');
    expect(await deliveryAddressUpdatePage.getAddressInput()).to.eq('address', 'Expected Address value to be equals to address');

    await deliveryAddressUpdatePage.save();
    expect(await deliveryAddressUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await deliveryAddressComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last DeliveryAddress', async () => {
    const nbButtonsBeforeDelete = await deliveryAddressComponentsPage.countDeleteButtons();
    await deliveryAddressComponentsPage.clickOnLastDeleteButton();

    deliveryAddressDeleteDialog = new DeliveryAddressDeleteDialog();
    expect(await deliveryAddressDeleteDialog.getDialogTitle()).to.eq('jhrApp.deliveryAddress.delete.question');
    await deliveryAddressDeleteDialog.clickOnConfirmButton();

    expect(await deliveryAddressComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
