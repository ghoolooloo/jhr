import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MerchantComponentsPage, MerchantDeleteDialog, MerchantUpdatePage } from './merchant.page-object';

const expect = chai.expect;

describe('Merchant e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let merchantComponentsPage: MerchantComponentsPage;
  let merchantUpdatePage: MerchantUpdatePage;
  let merchantDeleteDialog: MerchantDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Merchants', async () => {
    await navBarPage.goToEntity('merchant');
    merchantComponentsPage = new MerchantComponentsPage();
    await browser.wait(ec.visibilityOf(merchantComponentsPage.title), 5000);
    expect(await merchantComponentsPage.getTitle()).to.eq('jhrApp.merchant.home.title');
    await browser.wait(ec.or(ec.visibilityOf(merchantComponentsPage.entities), ec.visibilityOf(merchantComponentsPage.noResult)), 1000);
  });

  it('should load create Merchant page', async () => {
    await merchantComponentsPage.clickOnCreateButton();
    merchantUpdatePage = new MerchantUpdatePage();
    expect(await merchantUpdatePage.getPageTitle()).to.eq('jhrApp.merchant.home.createOrEditLabel');
    await merchantUpdatePage.cancel();
  });

  it('should create and save Merchants', async () => {
    const nbButtonsBeforeCreate = await merchantComponentsPage.countDeleteButtons();

    await merchantComponentsPage.clickOnCreateButton();

    await promise.all([
      merchantUpdatePage.setNameInput('name'),
      merchantUpdatePage.setLogoInput('logo'),
      merchantUpdatePage.setTelInput('tel'),
      merchantUpdatePage.setEmailInput('A@at.X4_gU-.dW.5'),
      merchantUpdatePage.setWebsiteInput('website'),
      merchantUpdatePage.setIcpInput('icp'),
      merchantUpdatePage.setCreatedByInput('createdBy'),
      merchantUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      merchantUpdatePage.setLastModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      merchantUpdatePage.setLastModifiedByInput('lastModifiedBy')
    ]);

    expect(await merchantUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await merchantUpdatePage.getLogoInput()).to.eq('logo', 'Expected Logo value to be equals to logo');
    expect(await merchantUpdatePage.getTelInput()).to.eq('tel', 'Expected Tel value to be equals to tel');
    expect(await merchantUpdatePage.getEmailInput()).to.eq('A@at.X4_gU-.dW.5', 'Expected Email value to be equals to A@at.X4_gU-.dW.5');
    expect(await merchantUpdatePage.getWebsiteInput()).to.eq('website', 'Expected Website value to be equals to website');
    expect(await merchantUpdatePage.getIcpInput()).to.eq('icp', 'Expected Icp value to be equals to icp');
    expect(await merchantUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await merchantUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await merchantUpdatePage.getLastModifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastModifiedDate value to be equals to 2000-12-31'
    );
    expect(await merchantUpdatePage.getLastModifiedByInput()).to.eq(
      'lastModifiedBy',
      'Expected LastModifiedBy value to be equals to lastModifiedBy'
    );

    await merchantUpdatePage.save();
    expect(await merchantUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await merchantComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Merchant', async () => {
    const nbButtonsBeforeDelete = await merchantComponentsPage.countDeleteButtons();
    await merchantComponentsPage.clickOnLastDeleteButton();

    merchantDeleteDialog = new MerchantDeleteDialog();
    expect(await merchantDeleteDialog.getDialogTitle()).to.eq('jhrApp.merchant.delete.question');
    await merchantDeleteDialog.clickOnConfirmButton();

    expect(await merchantComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
