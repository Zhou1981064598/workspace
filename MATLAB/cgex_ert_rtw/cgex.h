/*
 * File: cgex.h
 *
 * Code generated for Simulink model 'cgex'.
 *
 * Model version                  : 1.6
 * Simulink Coder version         : 9.0 (R2018b) 24-May-2018
 * C/C++ source code generated on : Thu Feb 29 14:03:21 2024
 *
 * Target selection: ert.tlc
 * Embedded hardware selection: Intel->x86-64 (Windows64)
 * Code generation objectives: Unspecified
 * Validation result: Not run
 */

#ifndef RTW_HEADER_cgex_h_
#define RTW_HEADER_cgex_h_
#include <string.h>
#include <stddef.h>
#ifndef cgex_COMMON_INCLUDES_
# define cgex_COMMON_INCLUDES_
#include "rtwtypes.h"
#endif                                 /* cgex_COMMON_INCLUDES_ */

#include "cgex_types.h"

/* Macros for accessing real-time model data structure */
#ifndef rtmGetErrorStatus
# define rtmGetErrorStatus(rtm)        ((rtm)->errorStatus)
#endif

#ifndef rtmSetErrorStatus
# define rtmSetErrorStatus(rtm, val)   ((rtm)->errorStatus = (val))
#endif

/* External inputs (root inport signals with default storage) */
typedef struct {
  real_T x;                            /* '<Root>/Input' */
  real_T y;                            /* '<Root>/Input1' */
} ExtU_cgex_T;

/* External outputs (root outports fed by signals with default storage) */
typedef struct {
  real_T Out1;                         /* '<Root>/Out1' */
} ExtY_cgex_T;

/* Real-time Model Data Structure */
struct tag_RTM_cgex_T {
  const char_T * volatile errorStatus;
};

/* External inputs (root inport signals with default storage) */
extern ExtU_cgex_T cgex_U;

/* External outputs (root outports fed by signals with default storage) */
extern ExtY_cgex_T cgex_Y;

/*
 * Exported Global Parameters
 *
 * Note: Exported global parameters are tunable parameters with an exported
 * global storage class designation.  Code generation will declare the memory for
 * these parameters and exports their symbols.
 *
 */
extern real_T k;                       /* Variable: k
                                        * Referenced by: '<Root>/Gain'
                                        */

/* Model entry point functions */
extern void cgex_initialize(void);
extern void cgex_step(void);
extern void cgex_terminate(void);

/* Real-time Model object */
extern RT_MODEL_cgex_T *const cgex_M;

/*-
 * The generated code includes comments that allow you to trace directly
 * back to the appropriate location in the model.  The basic format
 * is <system>/block_name, where system is the system number (uniquely
 * assigned by Simulink) and block_name is the name of the block.
 *
 * Use the MATLAB hilite_system command to trace the generated code back
 * to the model.  For example,
 *
 * hilite_system('<S3>')    - opens system 3
 * hilite_system('<S3>/Kp') - opens and selects block Kp which resides in S3
 *
 * Here is the system hierarchy for this model
 *
 * '<Root>' : 'cgex'
 */
#endif                                 /* RTW_HEADER_cgex_h_ */

/*
 * File trailer for generated code.
 *
 * [EOF]
 */
