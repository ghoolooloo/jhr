import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { RewardPointsComponentsPage, RewardPointsDeleteDialog, RewardPointsUpdatePage } from './reward-points.page-object';

const expect = chai.expect;

describe('RewardPoints e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let rewardPointsComponentsPage: RewardPointsComponentsPage;
  let rewardPointsUpdatePage: RewardPointsUpdatePage;
  let rewardPointsDeleteDialog: RewardPointsDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load RewardPoints', async () => {
    await navBarPage.goToEntity('reward-points');
    rewardPointsComponentsPage = new RewardPointsComponentsPage();
    await browser.wait(ec.visibilityOf(rewardPointsComponentsPage.title), 5000);
    expect(await rewardPointsComponentsPage.getTitle()).to.eq('jhrApp.rewardPoints.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(rewardPointsComponentsPage.entities), ec.visibilityOf(rewardPointsComponentsPage.noResult)),
      1000
    );
  });

  it('should load create RewardPoints page', async () => {
    await rewardPointsComponentsPage.clickOnCreateButton();
    rewardPointsUpdatePage = new RewardPointsUpdatePage();
    expect(await rewardPointsUpdatePage.getPageTitle()).to.eq('jhrApp.rewardPoints.home.createOrEditLabel');
    await rewardPointsUpdatePage.cancel();
  });

  it('should create and save RewardPoints', async () => {
    const nbButtonsBeforeCreate = await rewardPointsComponentsPage.countDeleteButtons();

    await rewardPointsComponentsPage.clickOnCreateButton();

    await promise.all([
      rewardPointsUpdatePage.setMemberNameInput('memberName'),
      rewardPointsUpdatePage.setMemberSNInput('memberSN'),
      rewardPointsUpdatePage.setBalanceInput('5'),
      rewardPointsUpdatePage.setLastModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM')
    ]);

    expect(await rewardPointsUpdatePage.getMemberNameInput()).to.eq('memberName', 'Expected MemberName value to be equals to memberName');
    expect(await rewardPointsUpdatePage.getMemberSNInput()).to.eq('memberSN', 'Expected MemberSN value to be equals to memberSN');
    expect(await rewardPointsUpdatePage.getBalanceInput()).to.eq('5', 'Expected balance value to be equals to 5');
    expect(await rewardPointsUpdatePage.getLastModifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastModifiedDate value to be equals to 2000-12-31'
    );

    await rewardPointsUpdatePage.save();
    expect(await rewardPointsUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await rewardPointsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last RewardPoints', async () => {
    const nbButtonsBeforeDelete = await rewardPointsComponentsPage.countDeleteButtons();
    await rewardPointsComponentsPage.clickOnLastDeleteButton();

    rewardPointsDeleteDialog = new RewardPointsDeleteDialog();
    expect(await rewardPointsDeleteDialog.getDialogTitle()).to.eq('jhrApp.rewardPoints.delete.question');
    await rewardPointsDeleteDialog.clickOnConfirmButton();

    expect(await rewardPointsComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
