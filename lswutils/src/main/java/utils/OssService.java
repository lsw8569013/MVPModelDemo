package utils;

import android.content.Context;
import android.util.Log;

import java.io.File;

/**
 * Created by mOss on 2015/12/7 0007.
 * 支持普通上传，普通下载
 */
public class OssService {
/*

    public OSS mOss;
    private String mBucket;
    private String mCallbackAddress;
    private static String mResumableObjectKey = "resumableObject";
    private Context mContext;

    public OssService(String bucket , Context context, String endpoint) {
        this.mBucket = bucket;
        this.mContext = context;

        OSSCredentialProvider credentialProvider2 = new OSSPlainTextAKSKCredentialProvider("LTAIHSVLtwah9tMm", "Ls0QvsGSZsZ2xXcVKHFIp8wISN6rqo");

        ClientConfiguration conf = new ClientConfiguration();
        conf.setConnectionTimeout(15 * 1000); // 连接超时，默认15秒
        conf.setSocketTimeout(15 * 1000); // socket超时，默认15秒
        conf.setMaxConcurrentRequest(5); // 最大并发请求书，默认5个
        conf.setMaxErrorRetry(2); // 失败后最大重试次数，默认2次
        mOss = new OSSClient(mContext, endpoint, credentialProvider2, conf);
        OSSLog.enableLog();
    }






    public void setBucketName(String bucket) {
        this.mBucket = bucket;
    }

    public void initOss(OSS _oss) {
        this.mOss = _oss;
    }

    public void setCallbackAddress(String callbackAddress) {
        this.mCallbackAddress = callbackAddress;
    }

//    public void asyncGetImage(String object) {
//
//        final long get_start = System.currentTimeMillis();
//        if ((object == null) || object.equals("")) {
//            Log.w("AsyncGetImage", "ObjectNull");
//            return;
//        }
//
//        GetObjectRequest get = new GetObjectRequest(mBucket, object);
//        get.setCRC64(OSSRequest.CRC64Config.YES);
//        get.setProgressListener(new OSSProgressCallback<GetObjectRequest>() {
//            @Override
//            public void onProgress(GetObjectRequest request, long currentSize, long totalSize) {
//                Log.d("GetObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
//                int progress = (int) (100 * currentSize / totalSize);
//                mDisplayer.updateProgress(progress);
//                mDisplayer.displayInfo("下载进度: " + String.valueOf(progress) + "%");
//            }
//        });
//        OSSAsyncTask task = mOss.asyncGetObject(get, new OSSCompletedCallback<GetObjectRequest, GetObjectResult>() {
//            @Override
//            public void onSuccess(GetObjectRequest request, GetObjectResult result) {
//                // 请求成功
//                InputStream inputStream = result.getObjectContent();
//                //Bitmap bm = BitmapFactory.decodeStream(inputStream);
//                try {
//                    //需要根据对应的View大小来自适应缩放
//                    Bitmap bm = mDisplayer.autoResizeFromStream(inputStream);
//                    long get_end = System.currentTimeMillis();
//                    OSSLog.logDebug("get cost: " + (get_end - get_start) / 1000f);
//                    mDisplayer.downloadComplete(bm);
//                    mDisplayer.displayInfo("Bucket: " + mBucket + "\nObject: " + request.getObjectKey() + "\nRequestId: " + result.getRequestId());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//
//            @Override
//            public void onFailure(GetObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
//                String info = "";
//                // 请求异常
//                if (clientExcepion != null) {
//                    // 本地异常如网络异常等
//                    clientExcepion.printStackTrace();
//                    info = clientExcepion.toString();
//                }
//                if (serviceException != null) {
//                    // 服务异常
//                    Log.e("ErrorCode", serviceException.getErrorCode());
//                    Log.e("RequestId", serviceException.getRequestId());
//                    Log.e("HostId", serviceException.getHostId());
//                    Log.e("RawMessage", serviceException.getRawMessage());
//                    info = serviceException.toString();
//                }
//                mDisplayer.downloadFail(info);
//                mDisplayer.displayInfo(info);
//            }
//        });
//    }

    */
/**
     * 服务器地址路径
     * @param object
     * @param localFile
     * @param uploadCallBack
     *//*

    public void asyncPutImage(String object, final String localFile, final UploadCallBack uploadCallBack) {
        final long upload_start = System.currentTimeMillis();

        if (object.equals("")) {
            Log.w("AsyncPutImage", "ObjectNull");
            return;
        }

        File file = new File(localFile);
        if (!file.exists()) {
            Log.w("AsyncPutImage", "FileNotExist");
            Log.w("LocalFile", localFile);
            return;
        }


        // 构造上传请求
        PutObjectRequest put = new PutObjectRequest(mBucket, object, localFile);
        put.setCRC64(OSSRequest.CRC64Config.YES);
//        if (mCallbackAddress != null) {
//            // 传入对应的上传回调参数，这里默认使用OSS提供的公共测试回调服务器地址
//            put.setCallbackParam(new HashMap<String, String>() {
//                {
//                    put("callbackUrl", mCallbackAddress);
//                    //callbackBody可以自定义传入的信息
//                    put("callbackBody", "filename=${object}");
//                }
//            });
//        }

        // 异步上传时可以设置进度回调
        put.setProgressCallback(new OSSProgressCallback<PutObjectRequest>() {
            @Override
            public void onProgress(PutObjectRequest request, long currentSize, long totalSize) {
                Log.d("PutObject", "currentSize: " + currentSize + " totalSize: " + totalSize);
                int progress = (int) (100 * currentSize / totalSize);

            }
        });

        OSSAsyncTask task = mOss.asyncPutObject(put, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
            @Override
            public void onSuccess(PutObjectRequest request, PutObjectResult result) {

                Log.e("PutObject", "UploadSuccess");
                Log.e("ETag", result.getETag());
                Log.e("RequestId", result.getRequestId());

                long upload_end = System.currentTimeMillis();
                OSSLog.logDebug("upload cost: " + (upload_end - upload_start) / 1000f);
                uploadCallBack.uploadSuccess(localFile);
                Log.e("uploadCallBack--- OK ", localFile);

            }

            @Override
            public void onFailure(PutObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                String info = "";
                // 请求异常
                uploadCallBack.onError();
                if (clientExcepion != null) {
                    // 本地异常如网络异常等
                    clientExcepion.printStackTrace();
                    info = clientExcepion.toString();
                }
                if (serviceException != null) {
                    // 服务异常
                    Log.e("ErrorCode", serviceException.getErrorCode());
                    Log.e("RequestId", serviceException.getRequestId());
                    Log.e("HostId", serviceException.getHostId());
                    Log.e("RawMessage", serviceException.getRawMessage());
                    info = serviceException.toString();
                }
            }
        });
    }


    */
/*private void asyncMultiPut(@NonNull List<String> pathList, @Nullable OSSCallback callback) {
        multiReset();
        final int size = pathList.size();
        for (int i = 0; i < size; i++) {
            String path = pathList.get(i);
            File file = new File(path);
            if (!file.exists()) {
                Timber.tag(TAG).e("file Not exist");
                return;
            }
            String objectKey = generateObjectKeyFromFile(file);
            mPathInfoMap.put(objectKey, new PathInfo(i, path));

            PutObjectRequest request = new PutObjectRequest(OssConfig.bucket, objectKey, file.getPath());
            request.setProgressCallback((request1, currentSize, totalSize) -> {
                mProgressMap.put(request1.getObjectKey(), new long[]{currentSize, totalSize});
                if (callback != null && mProgressMap.size() == size) {
                    long current = 0, total = 0;
                    for (Map.Entry<String, long[]> entry : mProgressMap.entrySet()) {
                        long[] value = entry.getValue();
                        current += value[0];
                        total += value[1];
                    }
                    float progress = current * 1.0f / total * 1.0f;
                    if (progress > 1) {
                        progress = 1;
                    }
                    final float finalProgress = progress;
                    Timber.tag(TAG).d("进度回调=" + finalProgress);
                    Completable.fromAction(() -> callback.onProgress(finalProgress))
                            .subscribeOn(AndroidSchedulers.mainThread())
                            .subscribe();
                }
            });
            OSSAsyncTask<PutObjectResult> asyncTask = mOssClient.asyncPutObject(request, new OSSCompletedCallback<PutObjectRequest, PutObjectResult>() {
                @Override
                public void onSuccess(PutObjectRequest request, PutObjectResult result) {
                    mPutCount++;
                    String requestObjectKey = request.getObjectKey();

                    //移除执行完毕的任务
                    mAsyncTaskMap.remove(requestObjectKey);

                    String filePath = mPathInfoMap.get(requestObjectKey).path;
                    Timber.tag(TAG).d("requestObjectKey=" + requestObjectKey + "->filePath=" + filePath);
                    mSuccessMap.put(filePath, requestObjectKey);

                    if (mPutCount == size) {
                        if (mSuccessMap.size() == size) {
                            //全部上传成功
                            if (callback != null) {
                                if (callback instanceof OSSMultiImageCallback) {
                                    callImagesCompleted((OSSMultiImageCallback) callback);
                                } else if (callback instanceof OSSVideoCallback) {
                                    callVideoCompleted(((OSSVideoCallback) callback));
                                }
                            }
                            multiReset();
                        } else {
                            //部分上传成功
                            for (Map.Entry<String, String> entry : mSuccessMap.entrySet()) {
                                String value = entry.getValue();
                                asyncDelete(value);
                            }
                            multiReset();
                            if (callback != null) {
                                Completable.fromAction(callback::onError)
                                        .subscribeOn(AndroidSchedulers.mainThread())
                                        .subscribe();
                            }
                        }
                    }
                }

                @Override
                public void onFailure(PutObjectRequest request, ClientException clientException, ServiceException serviceException) {
                    mPutCount++;
                    //移除执行完毕的任务
                    mAsyncTaskMap.remove(request.getObjectKey());

                    if (mPutCount == size) {
                        //上传失败
                        for (Map.Entry<String, String> entry : mSuccessMap.entrySet()) {
                            String value = entry.getValue();
                            asyncDelete(value);
                        }
                        multiReset();
                        if (callback != null) {
                            Completable.fromAction(callback::onError)
                                    .subscribeOn(AndroidSchedulers.mainThread())
                                    .subscribe();
                        }
                    }
                }
            });

            mAsyncTaskMap.put(objectKey, asyncTask);
        }
    }*//*


    // Downloads the files with specified prefix in the asynchronous way.
    public void asyncListObjectsWithBucketName() {
        ListObjectsRequest listObjects = new ListObjectsRequest(mBucket);
        // Sets the prefix
        listObjects.setPrefix("android");
        listObjects.setDelimiter("/");
        // Sets the success and failure callback. calls the Async API
        OSSAsyncTask task = mOss.asyncListObjects(listObjects, new OSSCompletedCallback<ListObjectsRequest, ListObjectsResult>() {
            @Override
            public void onSuccess(ListObjectsRequest request, ListObjectsResult result) {
                String info = "";
                OSSLog.logDebug("AyncListObjects", "Success!");
                for (int i = 0; i < result.getObjectSummaries().size(); i++) {
                    info += "\n" + String.format("object: %s %s %s", result.getObjectSummaries().get(i).getKey(), result.getObjectSummaries().get(i).getETag(), result.getObjectSummaries().get(i).getLastModified().toString());
                    OSSLog.logDebug("AyncListObjects", info);
                }

            }

            @Override
            public void onFailure(ListObjectsRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // request exception
                if (clientExcepion != null) {
                    // client side exception such as network exception
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // service side exception.
                    OSSLog.logError("ErrorCode", serviceException.getErrorCode());
                    OSSLog.logError("RequestId", serviceException.getRequestId());
                    OSSLog.logError("HostId", serviceException.getHostId());
                    OSSLog.logError("RawMessage", serviceException.getRawMessage());
                }

            }
        });
    }

    // Gets file's metadata
    public void headObject(String objectKey) {
        // Creates a request to get the file's metadata
        HeadObjectRequest head = new HeadObjectRequest(mBucket, objectKey);

        OSSAsyncTask task = mOss.asyncHeadObject(head, new OSSCompletedCallback<HeadObjectRequest, HeadObjectResult>() {
            @Override
            public void onSuccess(HeadObjectRequest request, HeadObjectResult result) {
                OSSLog.logDebug("headObject", "object Size: " + result.getMetadata().getContentLength());
                OSSLog.logDebug("headObject", "object Content Type: " + result.getMetadata().getContentType());


            }

            @Override
            public void onFailure(HeadObjectRequest request, ClientException clientExcepion, ServiceException serviceException) {
                // request exception
                if (clientExcepion != null) {
                    // client side exception,  such as network exception
                    clientExcepion.printStackTrace();
                }
                if (serviceException != null) {
                    // service side exception
                    OSSLog.logError("ErrorCode", serviceException.getErrorCode());
                    OSSLog.logError("RequestId", serviceException.getRequestId());
                    OSSLog.logError("HostId", serviceException.getHostId());
                    OSSLog.logError("RawMessage", serviceException.getRawMessage());
                }

            }
        });
    }

    public void asyncMultipartUpload(String uploadKey, String uploadFilePath) {
        MultipartUploadRequest request = new MultipartUploadRequest(mBucket, uploadKey,
                uploadFilePath);
        request.setCRC64(OSSRequest.CRC64Config.YES);
        request.setProgressCallback(new OSSProgressCallback<MultipartUploadRequest>() {

            @Override
            public void onProgress(MultipartUploadRequest request, long currentSize, long totalSize) {
                OSSLog.logDebug("[testMultipartUpload] - " + currentSize + " " + totalSize, false);
            }
        });
        mOss.asyncMultipartUpload(request, new OSSCompletedCallback<MultipartUploadRequest, CompleteMultipartUploadResult>() {
            @Override
            public void onSuccess(MultipartUploadRequest request, CompleteMultipartUploadResult result) {

            }

            @Override
            public void onFailure(MultipartUploadRequest request, ClientException clientException, ServiceException serviceException) {
                if (clientException != null) {

                } else if (serviceException != null) {

                }

            }
        });
    }

*/

    public interface UploadCallBack {
        void uploadSuccess(String result);

        void onError();
    }
}
