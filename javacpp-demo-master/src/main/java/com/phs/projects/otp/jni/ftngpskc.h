/*
 * 版权信息: Copyright (c) 飞天诚信科技股份有限公司
 * 文件名称: ftngpskc.h
 * 文件描述: 动态口令认证引擎令牌文件相关接口
 * 时    间: 2011-03-03
 * 摘    要: 
 * 作    者: 王强
 * 版    本: 1.0
 */

#ifndef __FTNGPSKC_H__
#define __FTNGPSKC_H__

#include "ftauthng.h"

#ifdef __cplusplus
extern "C" {
#endif

/********************************************************************
 令牌PSKC文件相关API定义
 *******************************************************************/
/*函数名称: init_pskc
 *说    明: 打开XML种子文件, 进行格式分解，返回空间和总记录数
 *参    数: file 文件名称
 *          keyfile 密钥文件名称(对称密钥或RSA私钥)
 *          pass 密码(PBE密码或RSA私钥的保护密码)
 *          ctx 解密的空间, 使用uninit_pskc释放
 *          recs 总共的记录数
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int init_pskc(char *file, char *keyfile, char *pass, 
    void **ctx, unsigned int *recs);

/*函数名称: read_pskc_rec
 *说    明: 从解密的数据中读取记录
 *参    数: ctx 解密的空间
 *          pdata 组装成PDATA
 *          i 记录的索引号,从0开始
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int read_pskc_rec(void *ctx, StTokenData *pdata, unsigned int i);

/*函数名称: uninit_pskc
 *说    明: 清除XML种子文件解密的内存空间
 *参    数: ctx 解密的空间
 *返    回: FT_API_SUCC(0) 成功
 *          其它结果请参考返回结果码说明文档
 */
int uninit_pskc(void *ctx);

#ifdef __cplusplus
}
#endif

#endif //__FTNGPSKC_H__
