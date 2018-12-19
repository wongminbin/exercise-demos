/*
 * 版权信息: Copyright (c) 飞天诚信科技股份有限公司
 * 文件名称: ftauthng.h
 * 文件描述: 动态口令认证引擎相关接口
 * 时    间: 2011-03-03
 * 摘    要: 
 * 作    者: 王强
 * 版    本: 1.0
 */

#ifndef __FTAUTHNG_H__
#define __FTAUTHNG_H__

#ifdef WIN32
// WIN32平台下定义uint64_t类型
typedef unsigned __int64 uint64_t;
#else
#include <stdint.h>
#endif

#ifdef __cplusplus
extern "C" {
#endif

#define FTOTPAPI_VERSION "1.1.0"

//令牌私有数据(字符串型)长度
#ifndef MAX_PRIVATE_DATA_LEN
#define MAX_PRIVATE_DATA_LEN (1024)
#define MAX_PRIVATE_DATA_SIZE (MAX_PRIVATE_DATA_LEN + 1)
#endif

//令牌类型
#define HOTP 0          //OATH事件型(C100)
#define TOTP 1          //OATH时间型(C200)
#define C300 2          //OATH挑战应答&TOTP(C300)
#define C400 3          //USBKEY+OTP型(该值不会使用)
#define C500 4          //EMV型(2011-11-23 暂不支持)
#define C300_SM3 5      //国密标准挑战应答型&国密标准时间型
#define TOTP_SM3 6      //国密标准时间型令牌
#define MB_HOTP 7       //OATH事件型手机令牌
#define MB_TOTP 8       //OATH时间型手机令牌
#define MB_C300 9       //OATH挑战应答&TOTP手机令牌
#define C300H 10        //OATH挑战应答&HOTP
#define C300H_SM3 11    //国密标准挑战应答型&国密标准事件型
#define C300TH 12       //OATH挑战应答(事件因子周期性清零)&TOTP
#define C300TH_SM3 13   //国密标准挑战应答型(事件因子周期性清零)&国密标准时间型
#define C300TH_SM3_ABC 14 ////国密标准挑战应答型(事件因子周期性清零)&国密标准时间型(农行专用)

#define FTTOTP_SM3 17   //国密SM3型
#define FTTOTP_SHA1 18  //国际私有算法
#define SMSOTP 19       //短信令牌
#define NORMAL_CARD 20  //普通刮刮卡
#define MATRIX_CARD 21  //矩阵卡
#define HA_CARD 22      //主机认证型刮刮卡

//默认TOTP时钟周期
#define DEFAULT_TOTP_INTERVAL 60

//默认短信OTP存活周期
#define DEFAULT_SMSOTP_ITV 600

//短信OTP永久有效
#define PERMANENT_SMSOTP_ITV (0xffffffffU)

//令牌序列号最大长度
#define MAX_TOKENSN_LEN 32

//厂商ID长度
#define MAX_MECHID_LEN 4

//OCRASUITE长度
#define MAX_OCRASUITE_LEN 64

//用户登录名长度
#define MAX_USERID_LEN 32

//用户PIN码长度
#define MAX_PIN_LEN 32

//令牌共享密钥(二进制数据)长度
#define MAX_TOKENKEY_SIZE 64

//令牌共享密钥摘要数据长度
#define KEY_HASH_SIZE 2

//默认废止周期(单位: 秒), 该值为5年(366 * 5 * 24 * 3600)
#define DEFAULT_LIFE_CYCLE (158112000U)

//默认动态口令最大重试次数
#define DEFAULT_MAX_REPEAT 10

//重试次数最大值, 超出该值则按照默认值(DEFAULT_MAX_REPEAT)处理
#define MAX_REPEAT_VALUE 256

//最大重试次数, 取该值时认证引擎不限制认证失败重试
#define INFINITE_MAX_REPEAT (0xffffU)

#define DEFAULT_OTP_LEN 6   //默认动态口令长度
#define MIN_OTP_LEN 4       //最小OTP长度(宏基定义)
#define MAX_OTP_LEN 8       //最大OTP长度(宏基定义)

//默认事件令牌小窗口(认证窗口)
#define DEFAULT_EVENT_AUTH_WND 40

//默认事件令牌中窗口
#define DEFAULT_EVENT_MEDIUM_WND 100

//默认事件令牌大窗口(同步窗口)
#define DEFAULT_EVENT_SYNC_WND 200

//默认时间令牌小窗口(认证窗口)
#define DEFAULT_TIME_AUTH_WND 2

//默认时间令牌中窗口
#define DEFAULT_TIME_MEDIUM_WND 4

//默认时间令牌大窗口(同步窗口)
#define DEFAULT_TIME_SYNC_WND 40

//认证窗口调整为中窗口的默认周期(单位: 秒)2周 
#define DEFAULT_ADJUST_PERIOD 1209600

//锁定登录周期建议值(单位: 秒), 超过该值后自动解锁
#define SUGGEST_LOCK_INTERVAL (86400) //24小时

//永久锁定
#define PERMANENT_LOCK_INTERVAL (0xffffffffU)

//锁定登录默认周期为永久锁定
#define DEFAULT_LOCK_INTERVAL PERMANENT_LOCK_INTERVAL

//STAGE1定义
#define STAGE1_INIT     0   //初始状态
#define STAGE1_ASSIGNED 1   //已绑定用户

//STAGE2定义
#define STAGE2_DISABLE  0   //停用
#define STAGE2_ENABLE   1   //启用
#define STAGE2_LOCKED   2   //已锁定
#define STAGE2_LOST     3   //挂失

//STAGE3定义
#define STAGE3_NORMAL   0   //非下一次动态口令(正常状态)
#define STAGE3_NEEDNEXT 1   //需要下一次动态口令(第一次同步成功)

//OCRA SUITE相关
#define DEFAULT_CR_SUITE    "OCRA-1:HOTP-SHA1-6:QN06-T1M"
#define DEFAULT_SIG_SUITE   "OCRA-1:HOTP-SHA1-6:QN64-T1M"
#define DEFAULT_HA_SUITE    "OCRA-1:HOTP-SHA1-6:QN06"

#define SUTIE_ID_NULL       0

//SUITE_ID 01-10分配给OATH OCRA
#define SUITE_ID_01         1
#define CR_SUITE_01         DEFAULT_CR_SUITE
#define SIG_SUITE_01        DEFAULT_SIG_SUITE
#define HA_SUITE_01         DEFAULT_HA_SUITE

#define SUITE_ID_02         2
#define CR_SUITE_02         "OCRA-1:HOTP-SHA1-8:QN08-T1M"
#define SIG_SUITE_02        "OCRA-1:HOTP-SHA1-8:QN64-T1M"
#define HA_SUITE_02         "OCRA-1:HOTP-SHA1-8:QN08"

//国密规范的挑战应答SUITE相关
#define DEFAULT_CR_GMST     "OTP-SM3-6:C-QN06-T1M"
#define DEFAULT_SIG_GMST    "OTP-SM3-6:C-QN64-T1M"
#define DEFAULT_HA_GMST     "OTP-SM3-6:QN06"

//SUITE_ID 11-20分配给国密规范挑战应答
#define SUITE_ID_11         11
#define CR_SUITE_11         DEFAULT_CR_GMST
#define SIG_SUITE_11        DEFAULT_SIG_GMST
#define HA_SUITE_11         DEFAULT_HA_GMST

#define SUITE_ID_12         12
#define CR_SUITE_12         "OTP-SM3-8:C-QN08-T1M"
#define SIG_SUITE_12        "OTP-SM3-8:C-QN64-T1M"
#define HA_SUITE_12         "OTP-SM3-8:QN08"

//挑战值长度(此处定义的值与算法库中MAX_OCRA_CHALLENGE_LEN[SIZE]一致)
#define CHLG_LEN        (64)
#define CHLG_SIZE       (65)

//应答值长度(此处定义的值与算法库中MAX_OCRA_RESPONSE_LEN[SIZE]一致)
#define RESP_LEN        (10)
#define RESP_SIZE       (11)

//动态口令长度(此处定义的值与算法库中OTP_MAX_DIGITS + 1一致)
#define OTP_SIZE        (11)

//解锁码长度(与OTP_SIZE一致)
#define PUK_SIZE        (11)

//密钥更新应答最大长度(该长度满足目前所有密钥更新方案)
#define MAX_UP_RESP_LEN     (32)
#define MAX_UP_RESP_SIZE    (33)

//默认一级解锁码时钟周期(2小时)
#define DEFAULT_PUK_ITV     (7200)

//默认二级解锁码时钟周期(半小时)
#define DEFAULT_PUK2_ITV    (1800)

//手机令牌激活码最大长度(此处比密文激活码长度略大)
#define AC_MAX_LEN  (64)
#define AC_MAX_SIZE (65)

//私有数据保护密钥长度
#define PROTECT_KEY_SIZE (16)

//普通刮刮卡默认包含口令个数
#define DEFAULT_NORMALCARD_OTPS (30)

//主机认证卡默认包含口令个数
#define DEFAULT_HACARD_OTPS (35)

//矩阵卡默认使用次数
#define DEFAULT_MATRIXCARD_OTPS (1000)

//动态口令存活周期最小粒度(单位: 秒)
#define OTP_LIFE_GRANULARITY (30)

//动态口令(包括挑战应答及签名)的最大存活周期(单位: OTP_LIFE_GRANULARITY)
#define MAX_OTP_LIFE (240)

//产品相关信息
#define MAX_PRODUCT_INFO 150

//令牌私有数据结构体
typedef struct otp_pdata
{
    unsigned int token_type;//令牌类型
    unsigned int stage1;    //0 初始状态, 1 已绑定
    unsigned int stage2;    //0 停用, 1 启动, 2 已锁定, 3已挂失, 4第一次同步成功
    unsigned int stage3;    //0 非NEXT, 1 NEXT
    unsigned int birth;     //令牌密钥初始化时间, 相对于EPOCH的秒数
    unsigned int death;     //令牌自动废止时间, 相对于EPOCH的秒数, 超出则作废

    /****************************************************************
     动态口令算法相关
     ***************************************************************/
    unsigned int algid;     //算法标识
    unsigned int otp_len;   //OTP长度
    unsigned int t0;        //起始参考时间(短信OTP时间戳)
    unsigned int interval;  //间隔时间(短信OTP的存活周期)
    int drift;              //漂移次数

    //小窗口(认证窗口), 事件型默认为40, 时间型默认为2
    unsigned int auth_wnd;

    //中窗口, 事件默认为100, 时间默认为4
    unsigned int medium_wnd;

    //大窗口(同步窗口), 事件型默认值为200, 时间型默认值为40
    unsigned int sync_wnd;

    //带事件的挑战应答使用的窗口
    unsigned int cr_auth_wnd;

    unsigned int adj_period;    //认证窗口调整为中窗口大小的周期, 默认为2周

    unsigned int login_err;     //登录错误次数
    unsigned int max_repeat;    //动态口令最大重试次数
    unsigned int lock_time;     //上次锁定时间, stage2状态变化时间戳
    unsigned int lock_interval; //锁定最大维持周期(秒), 默认为永久锁定
    unsigned int drft_time;     //上次校准时钟漂移的时间戳
    unsigned int last_succ;     //上次认证成功系统本地时间

    unsigned int puk_itv;       //一级解锁码时钟周期
    unsigned int puk2_itv;      //二级解锁码时钟周期
    unsigned int upresp_len;    //密钥更新响应长度
    unsigned int st_new;        //是否为密钥更新完成待首次认证状态

    uint64_t auth_base;         //认证基数, 或TOTP上次登录成功令牌时间因子
    uint64_t cr_tmbase;         //带时间的挑战应答令牌上次成功令牌时间因子
    uint64_t cr_base;           //带时间/事件的挑战应答中的认证基数

    unsigned int key_len;                   //共享密钥(二进制格式)长度
    unsigned char key[MAX_TOKENKEY_SIZE];   //共享密钥(二进制格式)
    unsigned char key_hash[KEY_HASH_SIZE];  //共享密钥摘要

    char sn[MAX_TOKENSN_LEN + 1];           //令牌序列号

    char cr_suite[MAX_OCRASUITE_LEN + 1];   //挑战应答SUITE
    char sig_suite[MAX_OCRASUITE_LEN + 1];  //签名SUITE
    char ha_suite[MAX_OCRASUITE_LEN + 1];   //主机认证SUITE

    char userid[MAX_USERID_LEN + 1];        //令牌注册时的用户名
    char pin[MAX_PIN_LEN + 1];              //PIN码

    unsigned int k2_len;                    //新共享密钥长度
    unsigned char k2[MAX_TOKENKEY_SIZE];    //新共享密钥
    unsigned char k2_hash[KEY_HASH_SIZE];   //新共享密钥摘要

    unsigned int otp_lifecycle;             //动态口令的存活周期
    unsigned int set_otp;                   //动态口令保存时的时间戳
    char otp[OTP_SIZE];                     //动态口令

    unsigned int chp_lifecycle;             //挑战应答口令的存活周期
    unsigned int set_chp;                   //挑战应答口令保存时的时间戳
    char chlg[CHLG_SIZE];                   //挑战值
    char chp[OTP_SIZE];                     //挑战应答口令
} otp_pdata_t;

//令牌私有数据结构
typedef struct StTokenData
{
    otp_pdata_t pdata;                      //令牌数据结构
    char priv_data[MAX_PRIVATE_DATA_SIZE];  //私有数据
} StTokenData;

//MSCHAP版本
typedef enum
{
    MSCHAP_V1 = 1,
    MSCHAP_V2 = 2
} mschap_ver_t;

/********************************************************************
 动态口令相关API返回值定义
 *******************************************************************/

//成功
#define FT_API_SUCC             (0)

//无效令牌私有数据(结构)
#define FT_API_INVALID_PDATA    (1)

//令牌注册得用户名无效
#define FT_API_INVALID_USERNAME (2)

//设置的PIN码无效(或PIN码验证失败)
#define FT_API_INVALID_PIN      (3)

//无效动态口令
#define FT_API_INVALID_OTP      (4)

//令牌未注册用户
#define FT_API_NOT_ASSIGNED     (5)

//令牌已停用
#define FT_API_TOKEN_DISABLED   (6)

//令牌已挂失
#define FT_API_TOKEN_LOST       (7)

//令牌已注销
#define FT_API_TOKEN_LOGOFF     (8)

//令牌被锁定
#define FT_API_TOKEN_LOCKED     (9)

//令牌类型暂不支持
#define FT_API_INVALID_TKTYPE   (10)

//令牌已过期
#define FT_API_TOKEN_EXPIRED    (11)

//需要调用同步接口
#define FT_API_NEED_RESYNC      (12)

//动态口令被重放
#define FT_API_REPLAY_OTP       (13)

//令牌的密钥无效(可能被篡改或密钥数据过短)或保护密钥无效
#define FT_API_INVALID_TKKEY    (14)

//动态口令长度无效
#define FT_API_INVALID_OTPLEN   (15)

//参数错误
#define FT_API_INVALID_PARAM    (16)

//挑战应答算法初始化失败
#define FT_API_CR_ERR           (17)
#define FT_API_OCRA_ERR         FT_API_CR_ERR

//无效挑战值
#define FT_API_INVALID_CHLG     (18)

//需要验证动态口令
#define FT_API_NEED_VERIFY      (19)

//卡片令牌包含的动态口令已耗尽
#define FT_API_OTP_EXHAUSTION   (20)

//无效CHAP(或MSCHAP)挑战值
#define FT_API_INVALID_CHAPCHLG (21)

//无效CHAP(或MSCHAP)应答值
#define FT_API_INVALID_CHAPRESP (22)

//需要进行密钥更新
#define FT_API_NEED_UPK         (23)

//密钥已成功更新过, 不允许重复更新
#define FT_API_ALREADY_UPK      (24)

//第一次动态口令成功, 需要下一次动态口令
#define FT_API_NEXTOTP          (400)

//无效的令牌XML文件
#define FT_API_INVALID_XMLFILE  (500)

//无效的令牌记录索引(索引越界)
#define FT_API_INVALID_TKIDX    (501)

/********************************************************************
 动态口令相关API定义
 *******************************************************************/

/*函数名称: set_key
 *说    明: 设置私有数据的保护密钥, 一般在解析种子文件前调用, 然后必
 *          须在调用具体业务驱动前, 再使用该函数设置与先前解析种子文
 *          件时相同的保护密钥. 如不使用该函数, 则引擎内部使用默认保
 *          护密钥.
 *          注意: 私有数据保护密钥仅在解析种子文件前设置, 后续阶段不
 *          允许按需更新, 此函数不允许多线程调用
 *参    数: key 保护密钥(必须不小于16字节)
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int set_key(unsigned char key[PROTECT_KEY_SIZE]);

/*函数名称: enable_token
 *说    明: 令牌首次使用激活, 令牌解锁等功能
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int enable_token(StTokenData *pdata);

/*函数名称: disable_token
 *说    明: 令牌注销接口
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int disable_token(StTokenData *pdata);

/*函数名称: set_nextmode_threshold
 *说    明: 设置动态口令最大重试次数
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *          repeat 最大重试次数
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int set_nextmode_threshold(StTokenData *pdata, unsigned int repeat);

/*函数名称: set_wnd
 *说    明: 设置窗口值
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *          auth_wnd 小窗口值
 *          med_wnd 中窗口值
 *          sync_wnd 大窗口值
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int set_wnd(StTokenData *pdata, unsigned int auth_wnd, unsigned int med_wnd, 
        unsigned int sync_wnd);

/*函数名称: set_otp_reused
 *说    明: 设置动态口令是否可重复使用
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *          itv 可重复使用的周期(不超过MAX_OTP_LIFE, 若该值超出
 *              MAX_OTP_LIFE, 则自动设置为不可重复使用, 0值表示
 *              不可重复使用)
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int set_otp_reused(StTokenData *pdata, unsigned int itv);

/*函数名称: set_chpass_reused
 *说    明: 设置挑战应答或签名是否可重复使用
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *          itv 可重复使用的周期(不超过MAX_OTP_LIFE, 若该值超出
 *              MAX_OTP_LIFE, 则自动设置为不可重复使用, 0值表示
 *              不可重复使用)
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int set_chpass_reused(StTokenData *pdata, unsigned int itv);

/*函数名称: set_user_login
 *说    明: 令牌注册时设定令牌的用户名, 一般设定为令牌序号
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *          name 用户名
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int set_user_login(StTokenData *pdata, const char *name);

/*函数名称: set_pin
 *说    明: 设定令牌的pin码
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *          pin 需要设定的pin码
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int set_pin(StTokenData *pdata, const char *pin);

/*函数名称: get_passcode_time
 *说    明: 获取系统当前时间(check_password和resynch_token接口使用)
 *返    回: 当前时间相对EPOCH(1970-01-01 00:00:00 UTC)得时间差(单位: 秒)
 */
long get_passcode_time();

/*函数名称: check_password
 *说    明: 验证动态密码
 *参    数: tm 系统当前时间
 *          pdata 令牌私有数据及可见数据, 允许回写
 *          otp 动态密码
 *          pin pin码
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int check_password(long tm, StTokenData *pdata, const char *otp, 
    const char *pin);

/*函数名称: genotp
 *说    明: 产生当前动态密码
 *参    数: tm 系统当前时间
 *          pdata 令牌私有数据及可见数据, 允许回写
 *          otp 当前动态密码
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int genotp(long tm, StTokenData *pdata, char otp[OTP_SIZE]);

/*函数名称: resynch_token
 *说    明: 令牌同步操作
 *参    数: tm 系统当前时间
 *          pdata 令牌私有数据及可见数据, 允许回写
 *          otp 动态密码
 *返    回: FT_API_NEXTOTP(400) 第一次成功
 *          FT_API_SUCC(0) 第二次成功
 *          其它结果请参考返回结果码说明文档
 */
int resynch_token(long tm, StTokenData *pdata, char *otp);

/*函数名称: resynch2_token
 *说    明: 令牌同步操作
 *参    数: tm 系统当前时间
 *          pdata 令牌私有数据及可见数据, 允许回写
 *          otp_prev 前一个动态密码
 *          otp 当前动态密码
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int resynch2_token(long tm, StTokenData *pdata, char *otp_prev, char *otp);

/*函数名称: encode_pdata
 *说    明: 将StTokenData结构得pdata令牌结构转化为字符串格式, 
 *          保存在StTokenData结构得priv_data成员中
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int encode_pdata(StTokenData *pdata);

/*函数名称: decode_pdata
 *说    明: 将StTokenData结构得priv_data字符串格式数据转化为令牌结构, 
 *          保存在StTokenData结构得pdata成员中
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int decode_pdata(StTokenData *pdata);

/*函数名称: gen_chlg
 *说    明: 生成挑战值
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *          chlg 挑战值
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int gen_chlg(StTokenData *pdata, char chlg[CHLG_SIZE]);

/*函数名称: check_chpass
 *说    明: 验证挑战应答动态密码
 *参    数: tm 系统当前时间
 *          pdata 令牌私有数据及可见数据, 允许回写
 *          otp 动态密码
 *          pin pin码
 *          chlg 挑战值
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int check_chpass(long tm, StTokenData *pdata, const char *otp, 
    const char *pin, const char *chlg);

/*函数名称: gen_chpass
 *说    明: 产生当前挑战应答动态密码
 *参    数: tm 系统当前时间
 *          pdata 令牌私有数据及可见数据, 允许回写
 *          otp 当前动态密码
 *          chlg 挑战值
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int gen_chpass(long tm, StTokenData *pdata, char otp[RESP_SIZE], 
    const char *chlg);

/*函数名称: gen_hacode
 *说    明: 生成主机认证码
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *          chlg 挑战值(由设备产生)
 *          otp 主机认证码
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int gen_hacode(StTokenData *pdata, const char *chlg, char otp[RESP_SIZE]);

/*函数名称: get_puk
 *说    明: 获取一级解锁码
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *          chlg 解锁码挑战, 如果物理令牌的解锁码模式为挑战应答模式时
 *               此参数值为令牌实际产生的解锁挑战码; 若为其他模式, 则
 *               忽略该参数
 *          puk 解锁码
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int get_puk(StTokenData *pdata, const char *chlg, char puk[PUK_SIZE]);

/*函数名称: get_puk2
 *说    明: 获取二级解锁码
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *          chlg 解锁码挑战, 如果物理令牌的解锁码模式为挑战应答模式时
 *               此参数值为令牌实际产生的解锁挑战码; 若为其他模式, 则
 *               忽略该参数
 *          puk 解锁码
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int get_puk2(StTokenData *pdata, const char *chlg, char puk[PUK_SIZE]);

/*函数名称: check_sigpass
 *说    明: 验证交易签名
 *参    数: tm 系统当前时间
 *			pdata 令牌私有数据及可见数据, 允许回写
 *			otp 签名值
 *			pin PIN码
 *          sigdata 签名数据
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int check_sigpass(long tm, StTokenData *pdata, const char *otp, 
    const char *pin, const char *sigdata);

/*函数名称: gen_sigpass
 *说    明: 产生当前交易签名
 *参    数: tm 系统当前时间
 *			pdata 令牌私有数据及可见数据, 允许回写
 *			otp 当前签名值
 *          sigdata 签名数据
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int gen_sigpass(long tm, StTokenData *pdata, char otp[RESP_SIZE], 
    const char *sigdata);

/*函数名称: update_key
 *说    明: 密钥更新
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *			r_tk 令牌随机数(由令牌产生, 仅在密钥更新模式为令牌/服务器
 *               共同主导或者令牌主导时, 使用该值)
 *          tran_info 业务信息数据(如果为空, 则使用内部随机生成的数据)
 *          up_resp 密钥更新响应, 如果密钥更新模式为令牌/服务器共同主
 *                  导或者服务器主导, 则成功后会设置实际值
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int update_key(StTokenData *pdata, char *r_tk, char *tran_info, 
    char up_resp[MAX_UP_RESP_SIZE]);

/*函数名称: check_chap_pass
 *说    明: 验证CHAP动态口令
 *参    数: tm 当前时间
 *          pdata 令牌私有数据及可见数据, 允许回写
 *			chlg 挑战
 *          chlg_len 挑战长度
 *          pass 动态口令
 *          pass_len 口令长度
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int check_chap_pass(long tm, StTokenData *pdata, 
        const unsigned char *chlg, unsigned int chlg_len, 
        const unsigned char *pass, unsigned int pass_len);

/*函数名称: check_mschap_pass
 *说    明: 验证MSCHAP形式动态密码
 *参    数: tm 当前时间
 *          pdata 令牌私有数据及可见数据, 允许回写
 *          ver MSCHAP版本
 *          chlg 挑战
 *          chlg_len 挑战长度
 *          pass 动态密码
 *          pass_len 动态密码长度
 *          pswd_hash 密码HASH缓冲区
 *          hash_hash 密码HASH的摘要缓冲区
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int check_mschap_pass(long tm, StTokenData *pdata, mschap_ver_t ver, 
    const unsigned char *chlg, unsigned int chlg_len, 
    const unsigned char *pass, unsigned int pass_len, 
    unsigned char pswd_hash[16], unsigned char hash_hash[16]);

/*函数名称: gen_ac
 *说    明: 生成激活码(仅对手机令牌有效)
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *          udid 手机的UDID
 *          ap 激活密码
 *          ac 激活码
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int gen_ac(StTokenData *pdata, const char *udid, const char *ap, 
    char ac[AC_MAX_SIZE]);

/*函数名称: gen_ac_cipher
 *说    明: 生成密文激活码(仅对手机令牌有效)
 *参    数: pdata 令牌私有数据及可见数据, 允许回写
 *          udid 手机的UDID
 *          ap 激活密码
 *          rand_buf 随机数据
 *          ac 激活码
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int gen_ac_cipher(StTokenData *pdata, const char *udid, const char *ap, 
    const char *rand_buf, char ac[AC_MAX_SIZE]);

/*函数名称: dump_pdata
 *说    明: 输出令牌结构数据
 *参    数: pdata 令牌结构
 *返    回: 
 */
void dump_pdata(otp_pdata_t *pdata);

/*函数名称: get_version
 *说    明: 获取版本信息
 *返    回: 版本信息字符串
 */
char* get_version();

/********************************************************************
 令牌PSKC文件相关API定义
 *******************************************************************/
#include "ftngpskc.h"

#ifdef __cplusplus
}
#endif

#endif //__FTAUTHNG_H__
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      