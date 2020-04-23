import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { MemberComponentsPage, MemberDeleteDialog, MemberUpdatePage } from './member.page-object';

const expect = chai.expect;

describe('Member e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let memberComponentsPage: MemberComponentsPage;
  let memberUpdatePage: MemberUpdatePage;
  let memberDeleteDialog: MemberDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Members', async () => {
    await navBarPage.goToEntity('member');
    memberComponentsPage = new MemberComponentsPage();
    await browser.wait(ec.visibilityOf(memberComponentsPage.title), 5000);
    expect(await memberComponentsPage.getTitle()).to.eq('jhrApp.member.home.title');
    await browser.wait(ec.or(ec.visibilityOf(memberComponentsPage.entities), ec.visibilityOf(memberComponentsPage.noResult)), 1000);
  });

  it('should load create Member page', async () => {
    await memberComponentsPage.clickOnCreateButton();
    memberUpdatePage = new MemberUpdatePage();
    expect(await memberUpdatePage.getPageTitle()).to.eq('jhrApp.member.home.createOrEditLabel');
    await memberUpdatePage.cancel();
  });

  it('should create and save Members', async () => {
    const nbButtonsBeforeCreate = await memberComponentsPage.countDeleteButtons();

    await memberComponentsPage.clickOnCreateButton();

    await promise.all([
      memberUpdatePage.setNameInput('name'),
      memberUpdatePage.setSnInput('sn'),
      memberUpdatePage.setOpenIDInput('openID'),
      memberUpdatePage.setUnionIDInput('unionID'),
      memberUpdatePage.setProfilePictureInput('profilePicture'),
      memberUpdatePage.sexSelectLastOption(),
      memberUpdatePage.setCountryInput('country'),
      memberUpdatePage.setProvinceInput('province'),
      memberUpdatePage.setCityInput('city'),
      memberUpdatePage.setJoinDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      memberUpdatePage.setLastLoginDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM')
    ]);

    expect(await memberUpdatePage.getNameInput()).to.eq('name', 'Expected Name value to be equals to name');
    expect(await memberUpdatePage.getSnInput()).to.eq('sn', 'Expected Sn value to be equals to sn');
    expect(await memberUpdatePage.getOpenIDInput()).to.eq('openID', 'Expected OpenID value to be equals to openID');
    expect(await memberUpdatePage.getUnionIDInput()).to.eq('unionID', 'Expected UnionID value to be equals to unionID');
    expect(await memberUpdatePage.getProfilePictureInput()).to.eq(
      'profilePicture',
      'Expected ProfilePicture value to be equals to profilePicture'
    );
    expect(await memberUpdatePage.getCountryInput()).to.eq('country', 'Expected Country value to be equals to country');
    expect(await memberUpdatePage.getProvinceInput()).to.eq('province', 'Expected Province value to be equals to province');
    expect(await memberUpdatePage.getCityInput()).to.eq('city', 'Expected City value to be equals to city');
    expect(await memberUpdatePage.getJoinDateInput()).to.contain('2001-01-01T02:30', 'Expected joinDate value to be equals to 2000-12-31');
    expect(await memberUpdatePage.getLastLoginDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastLoginDate value to be equals to 2000-12-31'
    );

    await memberUpdatePage.save();
    expect(await memberUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await memberComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Member', async () => {
    const nbButtonsBeforeDelete = await memberComponentsPage.countDeleteButtons();
    await memberComponentsPage.clickOnLastDeleteButton();

    memberDeleteDialog = new MemberDeleteDialog();
    expect(await memberDeleteDialog.getDialogTitle()).to.eq('jhrApp.member.delete.question');
    await memberDeleteDialog.clickOnConfirmButton();

    expect(await memberComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
