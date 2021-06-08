package com.sz.apollo.ui.activity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.hysd.android.platform_ub.utils.StringUtils;
import com.sz.apollo.R;
import com.sz.apollo.ui.adapters.AdapterAddressBook;
import com.sz.apollo.ui.basic.BasicActivity;
import com.sz.apollo.ui.models.ContactAddressBean;
import com.sz.apollo.ui.utils.DaoUtil;
import com.sz.apollo.ui.utils.UiHelper;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 地址簿
 */
public class AddressBookAcy extends BasicActivity {
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.iv_right)
    ImageView ivRight;
    @BindView(R.id.edit_search)
    EditText editSearch;
    @BindView(R.id.iv_search)
    ImageView ivSearch;
    @BindView(R.id.lv_book)
    ListView lvBook;
    @BindView(R.id.ll_data)
    LinearLayout llData;
    @BindView(R.id.tv_empty)
    TextView tvEmpty;
    @BindView(R.id.ll_empty)
    LinearLayout llEmpty;

    private AdapterAddressBook adapterAddressBook;
    private List<ContactAddressBean> list;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_book);
        ButterKnife.bind(this);
        initView();
    }

    private void initView() {
        tvTitle.setText(R.string.tup91);
        ivRight.setImageResource(R.drawable.ic_add_contact);
        adapterAddressBook = new AdapterAddressBook(this);
        list = new ArrayList<>();
        list = DaoUtil.selectAllContact();
        lvBook.setAdapter(adapterAddressBook);
        if (list.size() > 0) {
            llData.setVisibility(View.VISIBLE);
            llEmpty.setVisibility(View.GONE);
            adapterAddressBook.setList(list);
        } else {
            llData.setVisibility(View.GONE);
            llEmpty.setVisibility(View.VISIBLE);
        }
        lvBook.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UiHelper.startContactDetailAcy(AddressBookAcy.this, list.get(position).getId());
            }
        });
        editSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (StringUtils.isEmpty(s.toString())) {
                    adapterAddressBook.setList(list);
                }
                if (null != list && list.size() > 0) {
                    lvBook.setVisibility(View.VISIBLE);
                    llEmpty.setVisibility(View.GONE);
                } else {
                    lvBook.setVisibility(View.GONE);
                    llEmpty.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    @OnClick({R.id.iv_right, R.id.iv_search})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_right:
                UiHelper.startAddAddressBookAcy(this);
                break;
            case R.id.iv_search:
                String content = editSearch.getText().toString().trim();
                if (!StringUtils.isEmpty(content)) {
                    List<ContactAddressBean> selectedList = DaoUtil.selectContactFromAddress(content);
                    if (null != selectedList && selectedList.size() > 0) {
                        adapterAddressBook.setList(selectedList);
                        lvBook.setVisibility(View.VISIBLE);
                        llEmpty.setVisibility(View.GONE);
                    } else {
                        lvBook.setVisibility(View.GONE);
                        llEmpty.setVisibility(View.VISIBLE);
                    }
                }
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        if (list == null) {
            list = new ArrayList<>();
        } else {
            list.clear();
        }
        list = DaoUtil.selectAllContact();
        if (list.size() > 0) {
            llData.setVisibility(View.VISIBLE);
            llEmpty.setVisibility(View.GONE);
            adapterAddressBook.setList(list);
        } else {
            llData.setVisibility(View.GONE);
            llEmpty.setVisibility(View.VISIBLE);
        }
        if (null != adapterAddressBook) {
            adapterAddressBook.setList(list);
        }
    }
}
