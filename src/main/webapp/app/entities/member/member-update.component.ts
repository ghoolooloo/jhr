import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IMember, Member } from 'app/shared/model/member.model';
import { MemberService } from './member.service';

@Component({
  selector: 'jhi-member-update',
  templateUrl: './member-update.component.html'
})
export class MemberUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    name: [null, [Validators.required, Validators.maxLength(20)]],
    sn: [null, [Validators.required, Validators.maxLength(20)]],
    openID: [null, [Validators.maxLength(32)]],
    unionID: [null, [Validators.maxLength(32)]],
    profilePicture: [null, [Validators.maxLength(200)]],
    sex: [],
    country: [null, [Validators.maxLength(20)]],
    province: [null, [Validators.maxLength(20)]],
    city: [null, [Validators.maxLength(20)]],
    joinDate: [null, [Validators.required]],
    lastLoginDate: []
  });

  constructor(protected memberService: MemberService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ member }) => {
      if (!member.id) {
        const today = moment().startOf('day');
        member.joinDate = today;
        member.lastLoginDate = today;
      }

      this.updateForm(member);
    });
  }

  updateForm(member: IMember): void {
    this.editForm.patchValue({
      id: member.id,
      name: member.name,
      sn: member.sn,
      openID: member.openID,
      unionID: member.unionID,
      profilePicture: member.profilePicture,
      sex: member.sex,
      country: member.country,
      province: member.province,
      city: member.city,
      joinDate: member.joinDate ? member.joinDate.format(DATE_TIME_FORMAT) : null,
      lastLoginDate: member.lastLoginDate ? member.lastLoginDate.format(DATE_TIME_FORMAT) : null
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const member = this.createFromForm();
    if (member.id !== undefined) {
      this.subscribeToSaveResponse(this.memberService.update(member));
    } else {
      this.subscribeToSaveResponse(this.memberService.create(member));
    }
  }

  private createFromForm(): IMember {
    return {
      ...new Member(),
      id: this.editForm.get(['id'])!.value,
      name: this.editForm.get(['name'])!.value,
      sn: this.editForm.get(['sn'])!.value,
      openID: this.editForm.get(['openID'])!.value,
      unionID: this.editForm.get(['unionID'])!.value,
      profilePicture: this.editForm.get(['profilePicture'])!.value,
      sex: this.editForm.get(['sex'])!.value,
      country: this.editForm.get(['country'])!.value,
      province: this.editForm.get(['province'])!.value,
      city: this.editForm.get(['city'])!.value,
      joinDate: this.editForm.get(['joinDate'])!.value ? moment(this.editForm.get(['joinDate'])!.value, DATE_TIME_FORMAT) : undefined,
      lastLoginDate: this.editForm.get(['lastLoginDate'])!.value
        ? moment(this.editForm.get(['lastLoginDate'])!.value, DATE_TIME_FORMAT)
        : undefined
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IMember>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
