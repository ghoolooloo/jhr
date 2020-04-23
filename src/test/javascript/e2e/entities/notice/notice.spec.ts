import { browser, ExpectedConditions as ec, protractor, promise } from 'protractor';
import { NavBarPage, SignInPage } from '../../page-objects/jhi-page-objects';

import { NoticeComponentsPage, NoticeDeleteDialog, NoticeUpdatePage } from './notice.page-object';

const expect = chai.expect;

describe('Notice e2e test', () => {
  let navBarPage: NavBarPage;
  let signInPage: SignInPage;
  let noticeComponentsPage: NoticeComponentsPage;
  let noticeUpdatePage: NoticeUpdatePage;
  let noticeDeleteDialog: NoticeDeleteDialog;

  before(async () => {
    await browser.get('/');
    navBarPage = new NavBarPage();
    signInPage = await navBarPage.getSignInPage();
    await signInPage.autoSignInUsing('admin', 'admin');
    await browser.wait(ec.visibilityOf(navBarPage.entityMenu), 5000);
  });

  it('should load Notices', async () => {
    await navBarPage.goToEntity('notice');
    noticeComponentsPage = new NoticeComponentsPage();
    await browser.wait(ec.visibilityOf(noticeComponentsPage.title), 5000);
    expect(await noticeComponentsPage.getTitle()).to.eq('jhrApp.notice.home.title');
    await browser.wait(ec.or(ec.visibilityOf(noticeComponentsPage.entities), ec.visibilityOf(noticeComponentsPage.noResult)), 1000);
  });

  it('should load create Notice page', async () => {
    await noticeComponentsPage.clickOnCreateButton();
    noticeUpdatePage = new NoticeUpdatePage();
    expect(await noticeUpdatePage.getPageTitle()).to.eq('jhrApp.notice.home.createOrEditLabel');
    await noticeUpdatePage.cancel();
  });

  it('should create and save Notices', async () => {
    const nbButtonsBeforeCreate = await noticeComponentsPage.countDeleteButtons();

    await noticeComponentsPage.clickOnCreateButton();

    await promise.all([
      noticeUpdatePage.setContentInput('content'),
      noticeUpdatePage.setSortInput('5'),
      noticeUpdatePage.statusSelectLastOption(),
      noticeUpdatePage.setCreatedByInput('createdBy'),
      noticeUpdatePage.setCreatedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      noticeUpdatePage.setLastModifiedDateInput('01/01/2001' + protractor.Key.TAB + '02:30AM'),
      noticeUpdatePage.setLastModifiedByInput('lastModifiedBy')
    ]);

    expect(await noticeUpdatePage.getContentInput()).to.eq('content', 'Expected Content value to be equals to content');
    expect(await noticeUpdatePage.getSortInput()).to.eq('5', 'Expected sort value to be equals to 5');
    expect(await noticeUpdatePage.getCreatedByInput()).to.eq('createdBy', 'Expected CreatedBy value to be equals to createdBy');
    expect(await noticeUpdatePage.getCreatedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected createdDate value to be equals to 2000-12-31'
    );
    expect(await noticeUpdatePage.getLastModifiedDateInput()).to.contain(
      '2001-01-01T02:30',
      'Expected lastModifiedDate value to be equals to 2000-12-31'
    );
    expect(await noticeUpdatePage.getLastModifiedByInput()).to.eq(
      'lastModifiedBy',
      'Expected LastModifiedBy value to be equals to lastModifiedBy'
    );

    await noticeUpdatePage.save();
    expect(await noticeUpdatePage.getSaveButton().isPresent(), 'Expected save button disappear').to.be.false;

    expect(await noticeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeCreate + 1, 'Expected one more entry in the table');
  });

  it('should delete last Notice', async () => {
    const nbButtonsBeforeDelete = await noticeComponentsPage.countDeleteButtons();
    await noticeComponentsPage.clickOnLastDeleteButton();

    noticeDeleteDialog = new NoticeDeleteDialog();
    expect(await noticeDeleteDialog.getDialogTitle()).to.eq('jhrApp.notice.delete.question');
    await noticeDeleteDialog.clickOnConfirmButton();

    expect(await noticeComponentsPage.countDeleteButtons()).to.eq(nbButtonsBeforeDelete - 1);
  });

  after(async () => {
    await navBarPage.autoSignOut();
  });
});
