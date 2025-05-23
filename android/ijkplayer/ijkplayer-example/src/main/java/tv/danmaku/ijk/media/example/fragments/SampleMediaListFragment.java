/*
 * Copyright (C) 2015 Bilibili
 * Copyright (C) 2015 Zhang Rui <bbcallen@gmail.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package tv.danmaku.ijk.media.example.fragments;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import tv.danmaku.ijk.media.example.R;
import tv.danmaku.ijk.media.example.activities.VideoActivity;

public class SampleMediaListFragment extends Fragment {
    private ListView mFileListView;
    private SampleMediaAdapter mAdapter;
    private AlertDialog alertDialog;

    public static SampleMediaListFragment newInstance() {
        SampleMediaListFragment f = new SampleMediaListFragment();
        return f;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        ViewGroup viewGroup = (ViewGroup) inflater.inflate(R.layout.fragment_file_list, container, false);
        mFileListView = (ListView) viewGroup.findViewById(R.id.file_list_view);
        return viewGroup;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final Activity activity = getActivity();

        mAdapter = new SampleMediaAdapter(activity);
        mFileListView.setAdapter(mAdapter);
        mFileListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, final long id) {
                SampleMediaItem item = mAdapter.getItem(position);
                String name = item.mName;
                String url = item.mUrl;
                VideoActivity.intentTo(activity, url, name);
            }
        });
        mFileListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int position, long id) {
                final SampleMediaItem item = mAdapter.getItem(position);
                String url = item.mUrl;
                if (Build.VERSION.SDK_INT >= 21) {
                    alertDialog = new AlertDialog
                            .Builder(getContext())
                            .setView(LayoutInflater.from(getContext()).inflate(R.layout.layout_edit, (ViewGroup) null, true))
                            .setMessage("修改")
                            .setPositiveButton("确认", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                    item.mUrl = ((EditText) alertDialog.findViewById(R.id.ed_tv)).getText().toString();
                                    mAdapter.notifyDataSetChanged();
                                }
                            }).create();
                    alertDialog.show();
                    ((EditText) alertDialog.findViewById(R.id.ed_tv)).setText(url);
                }
                return true;
            }
        });

        String manifest_string =
                "{\n" +
                        "    \"version\": \"1.0.0\",\n" +
                        "    \"adaptationSet\": [\n" +
                        "        {\n" +
                        "            \"duration\": 1000,\n" +
                        "            \"id\": 1,\n" +
                        "            \"representation\": [\n" +
                        "                {\n" +
                        "                    \"id\": 1,\n" +
                        "                    \"codec\": \"avc1.64001e,mp4a.40.5\",\n" +
                        "                    \"url\": \"http://las-tech.org.cn/kwai/las-test_ld500d.flv\",\n" +
                        "                    \"backupUrl\": [],\n" +
                        "                    \"host\": \"las-tech.org.cn\",\n" +
                        "                    \"maxBitrate\": 700,\n" +
                        "                    \"width\": 640,\n" +
                        "                    \"height\": 360,\n" +
                        "                    \"frameRate\": 25,\n" +
                        "                    \"qualityType\": \"SMOOTH\",\n" +
                        "                    \"qualityTypeName\": \"流畅\",\n" +
                        "                    \"hidden\": false,\n" +
                        "                    \"disabledFromAdaptive\": false,\n" +
                        "                    \"defaultSelected\": false\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"id\": 2,\n" +
                        "                    \"codec\": \"avc1.64001f,mp4a.40.5\",\n" +
                        "                    \"url\": \"http://las-tech.org.cn/kwai/las-test_sd1000d.flv\",\n" +
                        "                    \"backupUrl\": [],\n" +
                        "                    \"host\": \"las-tech.org.cn\",\n" +
                        "                    \"maxBitrate\": 1300,\n" +
                        "                    \"width\": 960,\n" +
                        "                    \"height\": 540,\n" +
                        "                    \"frameRate\": 25,\n" +
                        "                    \"qualityType\": \"STANDARD\",\n" +
                        "                    \"qualityTypeName\": \"标清\",\n" +
                        "                    \"hidden\": false,\n" +
                        "                    \"disabledFromAdaptive\": false,\n" +
                        "                    \"defaultSelected\": true\n" +
                        "                },\n" +
                        "                {\n" +
                        "                    \"id\": 3,\n" +
                        "                    \"codec\": \"avc1.64001f,mp4a.40.5\",\n" +
                        "                    \"url\": \"http://las-tech.org.cn/kwai/las-test.flv\",\n" +
                        "                    \"backupUrl\": [],\n" +
                        "                    \"host\": \"las-tech.org.cn\",\n" +
                        "                    \"maxBitrate\": 2300,\n" +
                        "                    \"width\": 1280,\n" +
                        "                    \"height\": 720,\n" +
                        "                    \"frameRate\": 30,\n" +
                        "                    \"qualityType\": \"HIGH\",\n" +
                        "                    \"qualityTypeName\": \"高清\",\n" +
                        "                    \"hidden\": false,\n" +
                        "                    \"disabledFromAdaptive\": false,\n" +
                        "                    \"defaultSelected\": false\n" +
                        "                }\n" +
                        "            ]\n" +
                        "        }\n" +
                        "    ]\n" +
                        "}";
        mAdapter.addItem("rtsp://10.7.0.189:554/face", "rtsp");
        mAdapter.addItem("rtsp://10.10.2.17:554/pag://10.10.2.17:7302:35010000001310011390:0:MAIN:TCP", "dsfa");
        mAdapter.addItem("rtsp://61.156.103.73:554/PLTV/88888888/224/3221226043/10000100000000060000000000286778_0.smil", "dsfa");
        mAdapter.addItem(manifest_string, "las test");
        mAdapter.addItem("rtmp://tencent.a1000.top/live/room", "sei-rtmp");
        mAdapter.addItem("http://tencent.a1000.top:8088/live/room/index.m3u8", "sei-hls");
        mAdapter.addItem("rtmp://192.168.168.191/live/sei", "sei");
        mAdapter.addItem("https://56cf3370d8dd3.streamlock.net:1935/live/wfu.stream/chunklist_w760635728.m3u8", "wfu.edu");
        mAdapter.addItem("https://gcalic.v.myalicdn.com/gc/tms04_1/index.m3u8?contentid=2820180516001", "湖南-天门山云梦山顶");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8", "bipbop basic master playlist");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear1/prog_index.m3u8", "bipbop basic 400x300 @ 232 kbps");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear2/prog_index.m3u8", "bipbop basic 640x480 @ 650 kbps");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear3/prog_index.m3u8", "bipbop basic 640x480 @ 1 Mbps");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear4/prog_index.m3u8", "bipbop basic 960x720 @ 2 Mbps");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/gear0/prog_index.m3u8", "bipbop basic 22.050Hz stereo @ 40 kbps");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/bipbop_16x9_variant.m3u8", "bipbop advanced master playlist");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear1/prog_index.m3u8", "bipbop advanced 416x234 @ 265 kbps");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear2/prog_index.m3u8", "bipbop advanced 640x360 @ 580 kbps");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear3/prog_index.m3u8", "bipbop advanced 960x540 @ 910 kbps");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear4/prog_index.m3u8", "bipbop advanced 1289x720 @ 1 Mbps");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear5/prog_index.m3u8", "bipbop advanced 1920x1080 @ 2 Mbps");
        mAdapter.addItem("http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_16x9/gear0/prog_index.m3u8", "bipbop advanced 22.050Hz stereo @ 40 kbps");
    }

    final class SampleMediaItem {
        String mUrl;
        String mName;

        public SampleMediaItem(String url, String name) {
            mUrl = url;
            mName = name;
        }
    }

    final class SampleMediaAdapter extends ArrayAdapter<SampleMediaItem> {
        public SampleMediaAdapter(Context context) {
            super(context, android.R.layout.simple_list_item_2);
        }

        public void addItem(String url, String name) {
            add(new SampleMediaItem(url, name));
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view = convertView;
            if (view == null) {
                LayoutInflater inflater = LayoutInflater.from(parent.getContext());
                view = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
            }

            ViewHolder viewHolder = (ViewHolder) view.getTag();
            if (viewHolder == null) {
                viewHolder = new ViewHolder();
                viewHolder.mNameTextView = (TextView) view.findViewById(android.R.id.text1);
                viewHolder.mUrlTextView = (TextView) view.findViewById(android.R.id.text2);
            }

            SampleMediaItem item = getItem(position);
            viewHolder.mNameTextView.setText(item.mName);
            viewHolder.mUrlTextView.setText(item.mUrl);

            return view;
        }

        final class ViewHolder {
            public TextView mNameTextView;
            public TextView mUrlTextView;
        }
    }
}
