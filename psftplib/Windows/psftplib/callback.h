typedef enum callback_reason_tag{
CALLBACK_NO_REASON,
CALLBACK_KEY_DIFFERS,
CALLBACK_KEY_NEW,
CALLBACK_ACCESS_DENIED,
CALLBACK_REMOTE_DIRECTORY
} CALLBACK_REASON;

typedef int (*CB_FUNCTION)(CALLBACK_REASON res,void *lParam,void *wParam);
