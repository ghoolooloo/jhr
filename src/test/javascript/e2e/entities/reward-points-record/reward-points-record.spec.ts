import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import {
  RewardPointsRecordComponentsPage,
  RewardPointsRecordDeleteDialog,
  RewardPointsRecordUpdatePage
} from './reward-points-record.page-object';

const expect = chai.expect;

describe('RewardPointsRecord e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let rewardPointsRecordComponentsPage: RewardPointsRecordComponentsPage;
  let rewardPointsRecordUpdatePage: RewardPointsRecordUpdatePage;
  let rewardPointsRecordDeleteDialog: RewardPointsRecordDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load RewardPointsRecords', async () => {
    await navBarPage.goToEntity('reward-points-record');
    rewardPointsRecordComponentsPage = new RewardPointsRecordComponentsPage();
    await browser.wait(ec.visibilityOf(rewardPointsRecordComponentsPage.title), 5000);
    expect(await rewardPointsRecordComponentsPage.getTitle()).to.eq('jhrApp.rewardPointsRecord.home.title');
    await browser.wait(
      ec.or(ec.visibilityOf(rewardPointsRecordComponentsPage.entities), ec.visibilityOf(rewardPointsRecordComponentsPage.noResult)),
      1000
    );
  });

  it('should load create RewardPointsRecord page', async () => {
    await rewardPointsRecordComponentsPage.clickOnCreateButton();
    rewardPointsRecordUpdatePage = new RewardPointsRecordUpdatePage();
    expect(await rewardPointsRecordUpdatePage.getPageTitle()).to.eq('jhrApp.rewardPointsRecord.home.createOrEditLabel');
    await rewardPointsRecordUpdatePage.cancel();
  });

  it('should create and save RewardPointsRecords', async () => {
    const nbButtonsBeforeCreate = await rewardPointsRecordComponentsPage.countDeleteButtons();

    await rewardPointsRecordComponentsPage.clickOnCreateButton();

    await promise.all([
      rewardPointsRecordUpdatePage.setAmountInput('5'),
      rewardPointsRecordUpdatePage.setRemarkInput('remark'),
      rewardPointsRecordUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      rewardPointsRecordUpdatePage.rewardPointsSelectLastOption()
    ]);

    expect(await rewardPointsRecordUpdatePage.getAmountInput()).to.eq('5', 'Expected amount value to be equals to 5');
    expect(await rewardPointsRecordUpdatePage.getRemarkInput()).to.eq('remark', 'Expected Remark value to be equals to remark');
    expect(await rewardPointsRecordUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );

    await rewardPointsRecordUpdatePage.save();
    expect(await rewardPointsRecordUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await rewardPointsRecordComponentsPage.countDeleteButtons()).to.eq(
      nbButtonsBeforeCreate + 1,
      'Expected one more entry in the table'
    );
  });

  it('should delete last RewardPointsRecord', async () => {
    const nbButtonsBeforeDelete = await rewardPointsRecordComponentsPage.countDeleteButtons();
    await rewardPointsRecordComponentsPage.clickOnLastDeleteButton();

    rewardPointsRecordDeleteDialog = new RewardPointsRecordDeleteDialog();
    expect(await rewardPointsRecordDeleteDialog.getDialogTitle()).to.eq('jhrApp.rewardPointsRecord.delete.question');
    await rewardPointsRecordDeleteDialog.clickOnConfirmButton();

    expect(await rewardPointsRecordComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
