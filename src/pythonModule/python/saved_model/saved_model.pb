мс
Чж
^
AssignVariableOp
resource
value"dtype"
dtypetype"
validate_shapebool( И
А
BiasAdd

value"T	
bias"T
output"T""
Ttype:
2	"-
data_formatstringNHWC:
NHWCNCHW
N
Cast	
x"SrcT	
y"DstT"
SrcTtype"
DstTtype"
Truncatebool( 
8
Const
output"dtype"
valuetensor"
dtypetype
$
DisableCopyOnRead
resourceИ
.
Identity

input"T
output"T"	
Ttype
u
MatMul
a"T
b"T
product"T"
transpose_abool( "
transpose_bbool( "
Ttype:
2	
П
Mean

input"T
reduction_indices"Tidx
output"T"
	keep_dimsbool( ""
Ttype:
2	"
Tidxtype0:
2	
Ж
MergeV2Checkpoints
checkpoint_prefixes
destination_prefix"
delete_old_dirsbool("
allow_missing_filesbool( И

NoOp
M
Pack
values"T*N
output"T"
Nint(0"	
Ttype"
axisint 
C
Placeholder
output"dtype"
dtypetype"
shapeshape:
@
ReadVariableOp
resource
value"dtype"
dtypetypeИ
•
ResourceGather
resource
indices"Tindices
output"dtype"

batch_dimsint "
validate_indicesbool("
dtypetype"
Tindicestype:
2	И
o
	RestoreV2

prefix
tensor_names
shape_and_slices
tensors2dtypes"
dtypes
list(type)(0И
l
SaveV2

prefix
tensor_names
shape_and_slices
tensors2dtypes"
dtypes
list(type)(0И
?
Select
	condition

t"T
e"T
output"T"	
Ttype
H
ShardedFilename
basename	
shard

num_shards
filename
0
Sigmoid
x"T
y"T"
Ttype:

2
Ѕ
StatefulPartitionedCall
args2Tin
output2Tout"
Tin
list(type)("
Tout
list(type)("	
ffunc"
configstring "
config_protostring "
executor_typestring И®
@
StaticRegexFullMatch	
input

output
"
patternstring
N

StringJoin
inputs*N

output"
Nint(0"
	separatorstring 
Ц
VarHandleOp
resource"
	containerstring "
shared_namestring "
dtypetype"
shapeshape"#
allowed_deviceslist(string)
 И"serve*2.12.02v2.12.0-rc1-12-g0db597d0d758°р
^
countVarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_namecount
W
count/Read/ReadVariableOpReadVariableOpcount*
_output_shapes
: *
dtype0
^
totalVarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_nametotal
W
total/Read/ReadVariableOpReadVariableOptotal*
_output_shapes
: *
dtype0
b
count_1VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_name	count_1
[
count_1/Read/ReadVariableOpReadVariableOpcount_1*
_output_shapes
: *
dtype0
b
total_1VarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_name	total_1
[
total_1/Read/ReadVariableOpReadVariableOptotal_1*
_output_shapes
: *
dtype0
z
Adam/v/dense/biasVarHandleOp*
_output_shapes
: *
dtype0*
shape:*"
shared_nameAdam/v/dense/bias
s
%Adam/v/dense/bias/Read/ReadVariableOpReadVariableOpAdam/v/dense/bias*
_output_shapes
:*
dtype0
z
Adam/m/dense/biasVarHandleOp*
_output_shapes
: *
dtype0*
shape:*"
shared_nameAdam/m/dense/bias
s
%Adam/m/dense/bias/Read/ReadVariableOpReadVariableOpAdam/m/dense/bias*
_output_shapes
:*
dtype0
В
Adam/v/dense/kernelVarHandleOp*
_output_shapes
: *
dtype0*
shape
:*$
shared_nameAdam/v/dense/kernel
{
'Adam/v/dense/kernel/Read/ReadVariableOpReadVariableOpAdam/v/dense/kernel*
_output_shapes

:*
dtype0
В
Adam/m/dense/kernelVarHandleOp*
_output_shapes
: *
dtype0*
shape
:*$
shared_nameAdam/m/dense/kernel
{
'Adam/m/dense/kernel/Read/ReadVariableOpReadVariableOpAdam/m/dense/kernel*
_output_shapes

:*
dtype0
У
Adam/v/embedding/embeddingsVarHandleOp*
_output_shapes
: *
dtype0*
shape:	С*,
shared_nameAdam/v/embedding/embeddings
М
/Adam/v/embedding/embeddings/Read/ReadVariableOpReadVariableOpAdam/v/embedding/embeddings*
_output_shapes
:	С*
dtype0
У
Adam/m/embedding/embeddingsVarHandleOp*
_output_shapes
: *
dtype0*
shape:	С*,
shared_nameAdam/m/embedding/embeddings
М
/Adam/m/embedding/embeddings/Read/ReadVariableOpReadVariableOpAdam/m/embedding/embeddings*
_output_shapes
:	С*
dtype0
n
learning_rateVarHandleOp*
_output_shapes
: *
dtype0*
shape: *
shared_namelearning_rate
g
!learning_rate/Read/ReadVariableOpReadVariableOplearning_rate*
_output_shapes
: *
dtype0
f
	iterationVarHandleOp*
_output_shapes
: *
dtype0	*
shape: *
shared_name	iteration
_
iteration/Read/ReadVariableOpReadVariableOp	iteration*
_output_shapes
: *
dtype0	
l

dense/biasVarHandleOp*
_output_shapes
: *
dtype0*
shape:*
shared_name
dense/bias
e
dense/bias/Read/ReadVariableOpReadVariableOp
dense/bias*
_output_shapes
:*
dtype0
t
dense/kernelVarHandleOp*
_output_shapes
: *
dtype0*
shape
:*
shared_namedense/kernel
m
 dense/kernel/Read/ReadVariableOpReadVariableOpdense/kernel*
_output_shapes

:*
dtype0
Е
embedding/embeddingsVarHandleOp*
_output_shapes
: *
dtype0*
shape:	С*%
shared_nameembedding/embeddings
~
(embedding/embeddings/Read/ReadVariableOpReadVariableOpembedding/embeddings*
_output_shapes
:	С*
dtype0
В
serving_default_embedding_inputPlaceholder*'
_output_shapes
:€€€€€€€€€*
dtype0*
shape:€€€€€€€€€
р
StatefulPartitionedCallStatefulPartitionedCallserving_default_embedding_inputembedding/embeddingsdense/kernel
dense/bias*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*%
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *-
f(R&
$__inference_signature_wrapper_102756

NoOpNoOp
”"
ConstConst"/device:CPU:0*
_output_shapes
: *
dtype0*О"
valueД"BБ" Bъ!
І
layer_with_weights-0
layer-0
layer-1
layer_with_weights-1
layer-2
	variables
trainable_variables
regularization_losses
	keras_api
__call__
*	&call_and_return_all_conditional_losses

_default_save_signature
	optimizer

signatures*
†
	variables
trainable_variables
regularization_losses
	keras_api
__call__
*&call_and_return_all_conditional_losses

embeddings*
О
	variables
trainable_variables
regularization_losses
	keras_api
__call__
*&call_and_return_all_conditional_losses* 
¶
	variables
trainable_variables
regularization_losses
	keras_api
__call__
*&call_and_return_all_conditional_losses

 kernel
!bias*

0
 1
!2*

0
 1
!2*
* 
∞
"non_trainable_variables

#layers
$metrics
%layer_regularization_losses
&layer_metrics
	variables
trainable_variables
regularization_losses
__call__

_default_save_signature
*	&call_and_return_all_conditional_losses
&	"call_and_return_conditional_losses*
6
'trace_0
(trace_1
)trace_2
*trace_3* 
6
+trace_0
,trace_1
-trace_2
.trace_3* 
* 
Б
/
_variables
0_iterations
1_learning_rate
2_index_dict
3
_momentums
4_velocities
5_update_step_xla*

6serving_default* 

0*

0*
* 
У
7non_trainable_variables

8layers
9metrics
:layer_regularization_losses
;layer_metrics
	variables
trainable_variables
regularization_losses
__call__
*&call_and_return_all_conditional_losses
&"call_and_return_conditional_losses*

<trace_0* 

=trace_0* 
hb
VARIABLE_VALUEembedding/embeddings:layer_with_weights-0/embeddings/.ATTRIBUTES/VARIABLE_VALUE*
* 
* 
* 
С
>non_trainable_variables

?layers
@metrics
Alayer_regularization_losses
Blayer_metrics
	variables
trainable_variables
regularization_losses
__call__
*&call_and_return_all_conditional_losses
&"call_and_return_conditional_losses* 

Ctrace_0* 

Dtrace_0* 

 0
!1*

 0
!1*
* 
У
Enon_trainable_variables

Flayers
Gmetrics
Hlayer_regularization_losses
Ilayer_metrics
	variables
trainable_variables
regularization_losses
__call__
*&call_and_return_all_conditional_losses
&"call_and_return_conditional_losses*

Jtrace_0* 

Ktrace_0* 
\V
VARIABLE_VALUEdense/kernel6layer_with_weights-1/kernel/.ATTRIBUTES/VARIABLE_VALUE*
XR
VARIABLE_VALUE
dense/bias4layer_with_weights-1/bias/.ATTRIBUTES/VARIABLE_VALUE*
* 

0
1
2*

L0
M1*
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
5
00
N1
O2
P3
Q4
R5
S6*
SM
VARIABLE_VALUE	iteration0optimizer/_iterations/.ATTRIBUTES/VARIABLE_VALUE*
ZT
VARIABLE_VALUElearning_rate3optimizer/_learning_rate/.ATTRIBUTES/VARIABLE_VALUE*
* 

N0
P1
R2*

O0
Q1
S2*
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
* 
8
T	variables
U	keras_api
	Vtotal
	Wcount*
H
X	variables
Y	keras_api
	Ztotal
	[count
\
_fn_kwargs*
f`
VARIABLE_VALUEAdam/m/embedding/embeddings1optimizer/_variables/1/.ATTRIBUTES/VARIABLE_VALUE*
f`
VARIABLE_VALUEAdam/v/embedding/embeddings1optimizer/_variables/2/.ATTRIBUTES/VARIABLE_VALUE*
^X
VARIABLE_VALUEAdam/m/dense/kernel1optimizer/_variables/3/.ATTRIBUTES/VARIABLE_VALUE*
^X
VARIABLE_VALUEAdam/v/dense/kernel1optimizer/_variables/4/.ATTRIBUTES/VARIABLE_VALUE*
\V
VARIABLE_VALUEAdam/m/dense/bias1optimizer/_variables/5/.ATTRIBUTES/VARIABLE_VALUE*
\V
VARIABLE_VALUEAdam/v/dense/bias1optimizer/_variables/6/.ATTRIBUTES/VARIABLE_VALUE*

V0
W1*

T	variables*
UO
VARIABLE_VALUEtotal_14keras_api/metrics/0/total/.ATTRIBUTES/VARIABLE_VALUE*
UO
VARIABLE_VALUEcount_14keras_api/metrics/0/count/.ATTRIBUTES/VARIABLE_VALUE*

Z0
[1*

X	variables*
SM
VARIABLE_VALUEtotal4keras_api/metrics/1/total/.ATTRIBUTES/VARIABLE_VALUE*
SM
VARIABLE_VALUEcount4keras_api/metrics/1/count/.ATTRIBUTES/VARIABLE_VALUE*
* 
O
saver_filenamePlaceholder*
_output_shapes
: *
dtype0*
shape: 
Ю
StatefulPartitionedCall_1StatefulPartitionedCallsaver_filenameembedding/embeddingsdense/kernel
dense/bias	iterationlearning_rateAdam/m/embedding/embeddingsAdam/v/embedding/embeddingsAdam/m/dense/kernelAdam/v/dense/kernelAdam/m/dense/biasAdam/v/dense/biastotal_1count_1totalcountConst*
Tin
2*
Tout
2*
_collective_manager_ids
 *
_output_shapes
: * 
_read_only_resource_inputs
 *-
config_proto

CPU

GPU 2J 8В *(
f#R!
__inference__traced_save_102977
Щ
StatefulPartitionedCall_2StatefulPartitionedCallsaver_filenameembedding/embeddingsdense/kernel
dense/bias	iterationlearning_rateAdam/m/embedding/embeddingsAdam/v/embedding/embeddingsAdam/m/dense/kernelAdam/v/dense/kernelAdam/m/dense/biasAdam/v/dense/biastotal_1count_1totalcount*
Tin
2*
Tout
2*
_collective_manager_ids
 *
_output_shapes
: * 
_read_only_resource_inputs
 *-
config_proto

CPU

GPU 2J 8В *+
f&R$
"__inference__traced_restore_103032§•
в
Є
$__inference_signature_wrapper_102756
embedding_input
unknown:	С
	unknown_0:
	unknown_1:
identityИҐStatefulPartitionedCallћ
StatefulPartitionedCallStatefulPartitionedCallembedding_inputunknown	unknown_0	unknown_1*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*%
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В **
f%R#
!__inference__wrapped_model_102589o
IdentityIdentity StatefulPartitionedCall:output:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€`
NoOpNoOp^StatefulPartitionedCall*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*,
_input_shapes
:€€€€€€€€€: : : 22
StatefulPartitionedCallStatefulPartitionedCall:X T
'
_output_shapes
:€€€€€€€€€
)
_user_specified_nameembedding_input
ў
К
F__inference_sequential_layer_call_and_return_conditional_losses_102668

inputs#
embedding_102658:	С
dense_102662:
dense_102664:
identityИҐdense/StatefulPartitionedCallҐ!embedding/StatefulPartitionedCallд
!embedding/StatefulPartitionedCallStatefulPartitionedCallinputsembedding_102658*
Tin
2*
Tout
2*
_collective_manager_ids
 *+
_output_shapes
:€€€€€€€€€*#
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *N
fIRG
E__inference_embedding_layer_call_and_return_conditional_losses_102616ь
(global_average_pooling1d/PartitionedCallPartitionedCall*embedding/StatefulPartitionedCall:output:0*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€* 
_read_only_resource_inputs
 *-
config_proto

CPU

GPU 2J 8В *]
fXRV
T__inference_global_average_pooling1d_layer_call_and_return_conditional_losses_102596П
dense/StatefulPartitionedCallStatefulPartitionedCall1global_average_pooling1d/PartitionedCall:output:0dense_102662dense_102664*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*$
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *J
fERC
A__inference_dense_layer_call_and_return_conditional_losses_102632u
IdentityIdentity&dense/StatefulPartitionedCall:output:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€К
NoOpNoOp^dense/StatefulPartitionedCall"^embedding/StatefulPartitionedCall*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*,
_input_shapes
:€€€€€€€€€: : : 2>
dense/StatefulPartitionedCalldense/StatefulPartitionedCall2F
!embedding/StatefulPartitionedCall!embedding/StatefulPartitionedCall:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
ы
U
9__inference_global_average_pooling1d_layer_call_fn_102838

inputs
identity»
PartitionedCallPartitionedCallinputs*
Tin
2*
Tout
2*
_collective_manager_ids
 *0
_output_shapes
:€€€€€€€€€€€€€€€€€€* 
_read_only_resource_inputs
 *-
config_proto

CPU

GPU 2J 8В *]
fXRV
T__inference_global_average_pooling1d_layer_call_and_return_conditional_losses_102596i
IdentityIdentityPartitionedCall:output:0*
T0*0
_output_shapes
:€€€€€€€€€€€€€€€€€€"
identityIdentity:output:0*(
_construction_contextkEagerRuntime*<
_input_shapes+
):'€€€€€€€€€€€€€€€€€€€€€€€€€€€:e a
=
_output_shapes+
):'€€€€€€€€€€€€€€€€€€€€€€€€€€€
 
_user_specified_nameinputs
Ч

т
A__inference_dense_layer_call_and_return_conditional_losses_102864

inputs0
matmul_readvariableop_resource:-
biasadd_readvariableop_resource:
identityИҐBiasAdd/ReadVariableOpҐMatMul/ReadVariableOpt
MatMul/ReadVariableOpReadVariableOpmatmul_readvariableop_resource*
_output_shapes

:*
dtype0i
MatMulMatMulinputsMatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€r
BiasAdd/ReadVariableOpReadVariableOpbiasadd_readvariableop_resource*
_output_shapes
:*
dtype0v
BiasAddBiasAddMatMul:product:0BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
SigmoidSigmoidBiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Z
IdentityIdentitySigmoid:y:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€w
NoOpNoOp^BiasAdd/ReadVariableOp^MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime**
_input_shapes
:€€€€€€€€€: : 20
BiasAdd/ReadVariableOpBiasAdd/ReadVariableOp2.
MatMul/ReadVariableOpMatMul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
ф
У
F__inference_sequential_layer_call_and_return_conditional_losses_102639
embedding_input#
embedding_102617:	С
dense_102633:
dense_102635:
identityИҐdense/StatefulPartitionedCallҐ!embedding/StatefulPartitionedCallн
!embedding/StatefulPartitionedCallStatefulPartitionedCallembedding_inputembedding_102617*
Tin
2*
Tout
2*
_collective_manager_ids
 *+
_output_shapes
:€€€€€€€€€*#
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *N
fIRG
E__inference_embedding_layer_call_and_return_conditional_losses_102616ь
(global_average_pooling1d/PartitionedCallPartitionedCall*embedding/StatefulPartitionedCall:output:0*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€* 
_read_only_resource_inputs
 *-
config_proto

CPU

GPU 2J 8В *]
fXRV
T__inference_global_average_pooling1d_layer_call_and_return_conditional_losses_102596П
dense/StatefulPartitionedCallStatefulPartitionedCall1global_average_pooling1d/PartitionedCall:output:0dense_102633dense_102635*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*$
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *J
fERC
A__inference_dense_layer_call_and_return_conditional_losses_102632u
IdentityIdentity&dense/StatefulPartitionedCall:output:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€К
NoOpNoOp^dense/StatefulPartitionedCall"^embedding/StatefulPartitionedCall*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*,
_input_shapes
:€€€€€€€€€: : : 2>
dense/StatefulPartitionedCalldense/StatefulPartitionedCall2F
!embedding/StatefulPartitionedCall!embedding/StatefulPartitionedCall:X T
'
_output_shapes
:€€€€€€€€€
)
_user_specified_nameembedding_input
±B
р
"__inference__traced_restore_103032
file_prefix8
%assignvariableop_embedding_embeddings:	С1
assignvariableop_1_dense_kernel:+
assignvariableop_2_dense_bias:&
assignvariableop_3_iteration:	 *
 assignvariableop_4_learning_rate: A
.assignvariableop_5_adam_m_embedding_embeddings:	СA
.assignvariableop_6_adam_v_embedding_embeddings:	С8
&assignvariableop_7_adam_m_dense_kernel:8
&assignvariableop_8_adam_v_dense_kernel:2
$assignvariableop_9_adam_m_dense_bias:3
%assignvariableop_10_adam_v_dense_bias:%
assignvariableop_11_total_1: %
assignvariableop_12_count_1: #
assignvariableop_13_total: #
assignvariableop_14_count: 
identity_16ИҐAssignVariableOpҐAssignVariableOp_1ҐAssignVariableOp_10ҐAssignVariableOp_11ҐAssignVariableOp_12ҐAssignVariableOp_13ҐAssignVariableOp_14ҐAssignVariableOp_2ҐAssignVariableOp_3ҐAssignVariableOp_4ҐAssignVariableOp_5ҐAssignVariableOp_6ҐAssignVariableOp_7ҐAssignVariableOp_8ҐAssignVariableOp_9®
RestoreV2/tensor_namesConst"/device:CPU:0*
_output_shapes
:*
dtype0*ќ
valueƒBЅB:layer_with_weights-0/embeddings/.ATTRIBUTES/VARIABLE_VALUEB6layer_with_weights-1/kernel/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-1/bias/.ATTRIBUTES/VARIABLE_VALUEB0optimizer/_iterations/.ATTRIBUTES/VARIABLE_VALUEB3optimizer/_learning_rate/.ATTRIBUTES/VARIABLE_VALUEB1optimizer/_variables/1/.ATTRIBUTES/VARIABLE_VALUEB1optimizer/_variables/2/.ATTRIBUTES/VARIABLE_VALUEB1optimizer/_variables/3/.ATTRIBUTES/VARIABLE_VALUEB1optimizer/_variables/4/.ATTRIBUTES/VARIABLE_VALUEB1optimizer/_variables/5/.ATTRIBUTES/VARIABLE_VALUEB1optimizer/_variables/6/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/0/total/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/0/count/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/1/total/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/1/count/.ATTRIBUTES/VARIABLE_VALUEB_CHECKPOINTABLE_OBJECT_GRAPHР
RestoreV2/shape_and_slicesConst"/device:CPU:0*
_output_shapes
:*
dtype0*3
value*B(B B B B B B B B B B B B B B B B о
	RestoreV2	RestoreV2file_prefixRestoreV2/tensor_names:output:0#RestoreV2/shape_and_slices:output:0"/device:CPU:0*T
_output_shapesB
@::::::::::::::::*
dtypes
2	[
IdentityIdentityRestoreV2:tensors:0"/device:CPU:0*
T0*
_output_shapes
:Є
AssignVariableOpAssignVariableOp%assignvariableop_embedding_embeddingsIdentity:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0]

Identity_1IdentityRestoreV2:tensors:1"/device:CPU:0*
T0*
_output_shapes
:ґ
AssignVariableOp_1AssignVariableOpassignvariableop_1_dense_kernelIdentity_1:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0]

Identity_2IdentityRestoreV2:tensors:2"/device:CPU:0*
T0*
_output_shapes
:і
AssignVariableOp_2AssignVariableOpassignvariableop_2_dense_biasIdentity_2:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0]

Identity_3IdentityRestoreV2:tensors:3"/device:CPU:0*
T0	*
_output_shapes
:≥
AssignVariableOp_3AssignVariableOpassignvariableop_3_iterationIdentity_3:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0	]

Identity_4IdentityRestoreV2:tensors:4"/device:CPU:0*
T0*
_output_shapes
:Ј
AssignVariableOp_4AssignVariableOp assignvariableop_4_learning_rateIdentity_4:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0]

Identity_5IdentityRestoreV2:tensors:5"/device:CPU:0*
T0*
_output_shapes
:≈
AssignVariableOp_5AssignVariableOp.assignvariableop_5_adam_m_embedding_embeddingsIdentity_5:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0]

Identity_6IdentityRestoreV2:tensors:6"/device:CPU:0*
T0*
_output_shapes
:≈
AssignVariableOp_6AssignVariableOp.assignvariableop_6_adam_v_embedding_embeddingsIdentity_6:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0]

Identity_7IdentityRestoreV2:tensors:7"/device:CPU:0*
T0*
_output_shapes
:љ
AssignVariableOp_7AssignVariableOp&assignvariableop_7_adam_m_dense_kernelIdentity_7:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0]

Identity_8IdentityRestoreV2:tensors:8"/device:CPU:0*
T0*
_output_shapes
:љ
AssignVariableOp_8AssignVariableOp&assignvariableop_8_adam_v_dense_kernelIdentity_8:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0]

Identity_9IdentityRestoreV2:tensors:9"/device:CPU:0*
T0*
_output_shapes
:ї
AssignVariableOp_9AssignVariableOp$assignvariableop_9_adam_m_dense_biasIdentity_9:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0_
Identity_10IdentityRestoreV2:tensors:10"/device:CPU:0*
T0*
_output_shapes
:Њ
AssignVariableOp_10AssignVariableOp%assignvariableop_10_adam_v_dense_biasIdentity_10:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0_
Identity_11IdentityRestoreV2:tensors:11"/device:CPU:0*
T0*
_output_shapes
:і
AssignVariableOp_11AssignVariableOpassignvariableop_11_total_1Identity_11:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0_
Identity_12IdentityRestoreV2:tensors:12"/device:CPU:0*
T0*
_output_shapes
:і
AssignVariableOp_12AssignVariableOpassignvariableop_12_count_1Identity_12:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0_
Identity_13IdentityRestoreV2:tensors:13"/device:CPU:0*
T0*
_output_shapes
:≤
AssignVariableOp_13AssignVariableOpassignvariableop_13_totalIdentity_13:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0_
Identity_14IdentityRestoreV2:tensors:14"/device:CPU:0*
T0*
_output_shapes
:≤
AssignVariableOp_14AssignVariableOpassignvariableop_14_countIdentity_14:output:0"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtype0Y
NoOpNoOp"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 Щ
Identity_15Identityfile_prefix^AssignVariableOp^AssignVariableOp_1^AssignVariableOp_10^AssignVariableOp_11^AssignVariableOp_12^AssignVariableOp_13^AssignVariableOp_14^AssignVariableOp_2^AssignVariableOp_3^AssignVariableOp_4^AssignVariableOp_5^AssignVariableOp_6^AssignVariableOp_7^AssignVariableOp_8^AssignVariableOp_9^NoOp"/device:CPU:0*
T0*
_output_shapes
: W
Identity_16IdentityIdentity_15:output:0^NoOp_1*
T0*
_output_shapes
: Ж
NoOp_1NoOp^AssignVariableOp^AssignVariableOp_1^AssignVariableOp_10^AssignVariableOp_11^AssignVariableOp_12^AssignVariableOp_13^AssignVariableOp_14^AssignVariableOp_2^AssignVariableOp_3^AssignVariableOp_4^AssignVariableOp_5^AssignVariableOp_6^AssignVariableOp_7^AssignVariableOp_8^AssignVariableOp_9*"
_acd_function_control_output(*
_output_shapes
 "#
identity_16Identity_16:output:0*3
_input_shapes"
 : : : : : : : : : : : : : : : : 2*
AssignVariableOp_10AssignVariableOp_102*
AssignVariableOp_11AssignVariableOp_112*
AssignVariableOp_12AssignVariableOp_122*
AssignVariableOp_13AssignVariableOp_132*
AssignVariableOp_14AssignVariableOp_142(
AssignVariableOp_1AssignVariableOp_12(
AssignVariableOp_2AssignVariableOp_22(
AssignVariableOp_3AssignVariableOp_32(
AssignVariableOp_4AssignVariableOp_42(
AssignVariableOp_5AssignVariableOp_52(
AssignVariableOp_6AssignVariableOp_62(
AssignVariableOp_7AssignVariableOp_72(
AssignVariableOp_8AssignVariableOp_82(
AssignVariableOp_9AssignVariableOp_92$
AssignVariableOpAssignVariableOp:C ?

_output_shapes
: 
%
_user_specified_namefile_prefix
ф
У
F__inference_sequential_layer_call_and_return_conditional_losses_102652
embedding_input#
embedding_102642:	С
dense_102646:
dense_102648:
identityИҐdense/StatefulPartitionedCallҐ!embedding/StatefulPartitionedCallн
!embedding/StatefulPartitionedCallStatefulPartitionedCallembedding_inputembedding_102642*
Tin
2*
Tout
2*
_collective_manager_ids
 *+
_output_shapes
:€€€€€€€€€*#
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *N
fIRG
E__inference_embedding_layer_call_and_return_conditional_losses_102616ь
(global_average_pooling1d/PartitionedCallPartitionedCall*embedding/StatefulPartitionedCall:output:0*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€* 
_read_only_resource_inputs
 *-
config_proto

CPU

GPU 2J 8В *]
fXRV
T__inference_global_average_pooling1d_layer_call_and_return_conditional_losses_102596П
dense/StatefulPartitionedCallStatefulPartitionedCall1global_average_pooling1d/PartitionedCall:output:0dense_102646dense_102648*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*$
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *J
fERC
A__inference_dense_layer_call_and_return_conditional_losses_102632u
IdentityIdentity&dense/StatefulPartitionedCall:output:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€К
NoOpNoOp^dense/StatefulPartitionedCall"^embedding/StatefulPartitionedCall*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*,
_input_shapes
:€€€€€€€€€: : : 2>
dense/StatefulPartitionedCalldense/StatefulPartitionedCall2F
!embedding/StatefulPartitionedCall!embedding/StatefulPartitionedCall:X T
'
_output_shapes
:€€€€€€€€€
)
_user_specified_nameembedding_input
М
p
T__inference_global_average_pooling1d_layer_call_and_return_conditional_losses_102844

inputs
identityX
Mean/reduction_indicesConst*
_output_shapes
: *
dtype0*
value	B :p
MeanMeaninputsMean/reduction_indices:output:0*
T0*0
_output_shapes
:€€€€€€€€€€€€€€€€€€^
IdentityIdentityMean:output:0*
T0*0
_output_shapes
:€€€€€€€€€€€€€€€€€€"
identityIdentity:output:0*(
_construction_contextkEagerRuntime*<
_input_shapes+
):'€€€€€€€€€€€€€€€€€€€€€€€€€€€:e a
=
_output_shapes+
):'€€€€€€€€€€€€€€€€€€€€€€€€€€€
 
_user_specified_nameinputs
О
њ
+__inference_sequential_layer_call_fn_102677
embedding_input
unknown:	С
	unknown_0:
	unknown_1:
identityИҐStatefulPartitionedCallс
StatefulPartitionedCallStatefulPartitionedCallembedding_inputunknown	unknown_0	unknown_1*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*%
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *O
fJRH
F__inference_sequential_layer_call_and_return_conditional_losses_102668o
IdentityIdentity StatefulPartitionedCall:output:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€`
NoOpNoOp^StatefulPartitionedCall*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*,
_input_shapes
:€€€€€€€€€: : : 22
StatefulPartitionedCallStatefulPartitionedCall:X T
'
_output_shapes
:€€€€€€€€€
)
_user_specified_nameembedding_input
у
ґ
+__inference_sequential_layer_call_fn_102767

inputs
unknown:	С
	unknown_0:
	unknown_1:
identityИҐStatefulPartitionedCallи
StatefulPartitionedCallStatefulPartitionedCallinputsunknown	unknown_0	unknown_1*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*%
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *O
fJRH
F__inference_sequential_layer_call_and_return_conditional_losses_102668o
IdentityIdentity StatefulPartitionedCall:output:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€`
NoOpNoOp^StatefulPartitionedCall*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*,
_input_shapes
:€€€€€€€€€: : : 22
StatefulPartitionedCallStatefulPartitionedCall:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
Ђt
≥
__inference__traced_save_102977
file_prefix>
+read_disablecopyonread_embedding_embeddings:	С7
%read_1_disablecopyonread_dense_kernel:1
#read_2_disablecopyonread_dense_bias:,
"read_3_disablecopyonread_iteration:	 0
&read_4_disablecopyonread_learning_rate: G
4read_5_disablecopyonread_adam_m_embedding_embeddings:	СG
4read_6_disablecopyonread_adam_v_embedding_embeddings:	С>
,read_7_disablecopyonread_adam_m_dense_kernel:>
,read_8_disablecopyonread_adam_v_dense_kernel:8
*read_9_disablecopyonread_adam_m_dense_bias:9
+read_10_disablecopyonread_adam_v_dense_bias:+
!read_11_disablecopyonread_total_1: +
!read_12_disablecopyonread_count_1: )
read_13_disablecopyonread_total: )
read_14_disablecopyonread_count: 
savev2_const
identity_31ИҐMergeV2CheckpointsҐRead/DisableCopyOnReadҐRead/ReadVariableOpҐRead_1/DisableCopyOnReadҐRead_1/ReadVariableOpҐRead_10/DisableCopyOnReadҐRead_10/ReadVariableOpҐRead_11/DisableCopyOnReadҐRead_11/ReadVariableOpҐRead_12/DisableCopyOnReadҐRead_12/ReadVariableOpҐRead_13/DisableCopyOnReadҐRead_13/ReadVariableOpҐRead_14/DisableCopyOnReadҐRead_14/ReadVariableOpҐRead_2/DisableCopyOnReadҐRead_2/ReadVariableOpҐRead_3/DisableCopyOnReadҐRead_3/ReadVariableOpҐRead_4/DisableCopyOnReadҐRead_4/ReadVariableOpҐRead_5/DisableCopyOnReadҐRead_5/ReadVariableOpҐRead_6/DisableCopyOnReadҐRead_6/ReadVariableOpҐRead_7/DisableCopyOnReadҐRead_7/ReadVariableOpҐRead_8/DisableCopyOnReadҐRead_8/ReadVariableOpҐRead_9/DisableCopyOnReadҐRead_9/ReadVariableOpw
StaticRegexFullMatchStaticRegexFullMatchfile_prefix"/device:CPU:**
_output_shapes
: *
pattern
^s3://.*Z
ConstConst"/device:CPU:**
_output_shapes
: *
dtype0*
valueB B.parta
Const_1Const"/device:CPU:**
_output_shapes
: *
dtype0*
valueB B
_temp/partБ
SelectSelectStaticRegexFullMatch:output:0Const:output:0Const_1:output:0"/device:CPU:**
T0*
_output_shapes
: f

StringJoin
StringJoinfile_prefixSelect:output:0"/device:CPU:**
N*
_output_shapes
: L

num_shardsConst*
_output_shapes
: *
dtype0*
value	B :f
ShardedFilename/shardConst"/device:CPU:0*
_output_shapes
: *
dtype0*
value	B : У
ShardedFilenameShardedFilenameStringJoin:output:0ShardedFilename/shard:output:0num_shards:output:0"/device:CPU:0*
_output_shapes
: }
Read/DisableCopyOnReadDisableCopyOnRead+read_disablecopyonread_embedding_embeddings"/device:CPU:0*
_output_shapes
 ®
Read/ReadVariableOpReadVariableOp+read_disablecopyonread_embedding_embeddings^Read/DisableCopyOnRead"/device:CPU:0*
_output_shapes
:	С*
dtype0j
IdentityIdentityRead/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes
:	Сb

Identity_1IdentityIdentity:output:0"/device:CPU:0*
T0*
_output_shapes
:	Сy
Read_1/DisableCopyOnReadDisableCopyOnRead%read_1_disablecopyonread_dense_kernel"/device:CPU:0*
_output_shapes
 •
Read_1/ReadVariableOpReadVariableOp%read_1_disablecopyonread_dense_kernel^Read_1/DisableCopyOnRead"/device:CPU:0*
_output_shapes

:*
dtype0m

Identity_2IdentityRead_1/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes

:c

Identity_3IdentityIdentity_2:output:0"/device:CPU:0*
T0*
_output_shapes

:w
Read_2/DisableCopyOnReadDisableCopyOnRead#read_2_disablecopyonread_dense_bias"/device:CPU:0*
_output_shapes
 Я
Read_2/ReadVariableOpReadVariableOp#read_2_disablecopyonread_dense_bias^Read_2/DisableCopyOnRead"/device:CPU:0*
_output_shapes
:*
dtype0i

Identity_4IdentityRead_2/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes
:_

Identity_5IdentityIdentity_4:output:0"/device:CPU:0*
T0*
_output_shapes
:v
Read_3/DisableCopyOnReadDisableCopyOnRead"read_3_disablecopyonread_iteration"/device:CPU:0*
_output_shapes
 Ъ
Read_3/ReadVariableOpReadVariableOp"read_3_disablecopyonread_iteration^Read_3/DisableCopyOnRead"/device:CPU:0*
_output_shapes
: *
dtype0	e

Identity_6IdentityRead_3/ReadVariableOp:value:0"/device:CPU:0*
T0	*
_output_shapes
: [

Identity_7IdentityIdentity_6:output:0"/device:CPU:0*
T0	*
_output_shapes
: z
Read_4/DisableCopyOnReadDisableCopyOnRead&read_4_disablecopyonread_learning_rate"/device:CPU:0*
_output_shapes
 Ю
Read_4/ReadVariableOpReadVariableOp&read_4_disablecopyonread_learning_rate^Read_4/DisableCopyOnRead"/device:CPU:0*
_output_shapes
: *
dtype0e

Identity_8IdentityRead_4/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes
: [

Identity_9IdentityIdentity_8:output:0"/device:CPU:0*
T0*
_output_shapes
: И
Read_5/DisableCopyOnReadDisableCopyOnRead4read_5_disablecopyonread_adam_m_embedding_embeddings"/device:CPU:0*
_output_shapes
 µ
Read_5/ReadVariableOpReadVariableOp4read_5_disablecopyonread_adam_m_embedding_embeddings^Read_5/DisableCopyOnRead"/device:CPU:0*
_output_shapes
:	С*
dtype0o
Identity_10IdentityRead_5/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes
:	Сf
Identity_11IdentityIdentity_10:output:0"/device:CPU:0*
T0*
_output_shapes
:	СИ
Read_6/DisableCopyOnReadDisableCopyOnRead4read_6_disablecopyonread_adam_v_embedding_embeddings"/device:CPU:0*
_output_shapes
 µ
Read_6/ReadVariableOpReadVariableOp4read_6_disablecopyonread_adam_v_embedding_embeddings^Read_6/DisableCopyOnRead"/device:CPU:0*
_output_shapes
:	С*
dtype0o
Identity_12IdentityRead_6/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes
:	Сf
Identity_13IdentityIdentity_12:output:0"/device:CPU:0*
T0*
_output_shapes
:	СА
Read_7/DisableCopyOnReadDisableCopyOnRead,read_7_disablecopyonread_adam_m_dense_kernel"/device:CPU:0*
_output_shapes
 ђ
Read_7/ReadVariableOpReadVariableOp,read_7_disablecopyonread_adam_m_dense_kernel^Read_7/DisableCopyOnRead"/device:CPU:0*
_output_shapes

:*
dtype0n
Identity_14IdentityRead_7/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes

:e
Identity_15IdentityIdentity_14:output:0"/device:CPU:0*
T0*
_output_shapes

:А
Read_8/DisableCopyOnReadDisableCopyOnRead,read_8_disablecopyonread_adam_v_dense_kernel"/device:CPU:0*
_output_shapes
 ђ
Read_8/ReadVariableOpReadVariableOp,read_8_disablecopyonread_adam_v_dense_kernel^Read_8/DisableCopyOnRead"/device:CPU:0*
_output_shapes

:*
dtype0n
Identity_16IdentityRead_8/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes

:e
Identity_17IdentityIdentity_16:output:0"/device:CPU:0*
T0*
_output_shapes

:~
Read_9/DisableCopyOnReadDisableCopyOnRead*read_9_disablecopyonread_adam_m_dense_bias"/device:CPU:0*
_output_shapes
 ¶
Read_9/ReadVariableOpReadVariableOp*read_9_disablecopyonread_adam_m_dense_bias^Read_9/DisableCopyOnRead"/device:CPU:0*
_output_shapes
:*
dtype0j
Identity_18IdentityRead_9/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes
:a
Identity_19IdentityIdentity_18:output:0"/device:CPU:0*
T0*
_output_shapes
:А
Read_10/DisableCopyOnReadDisableCopyOnRead+read_10_disablecopyonread_adam_v_dense_bias"/device:CPU:0*
_output_shapes
 ©
Read_10/ReadVariableOpReadVariableOp+read_10_disablecopyonread_adam_v_dense_bias^Read_10/DisableCopyOnRead"/device:CPU:0*
_output_shapes
:*
dtype0k
Identity_20IdentityRead_10/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes
:a
Identity_21IdentityIdentity_20:output:0"/device:CPU:0*
T0*
_output_shapes
:v
Read_11/DisableCopyOnReadDisableCopyOnRead!read_11_disablecopyonread_total_1"/device:CPU:0*
_output_shapes
 Ы
Read_11/ReadVariableOpReadVariableOp!read_11_disablecopyonread_total_1^Read_11/DisableCopyOnRead"/device:CPU:0*
_output_shapes
: *
dtype0g
Identity_22IdentityRead_11/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes
: ]
Identity_23IdentityIdentity_22:output:0"/device:CPU:0*
T0*
_output_shapes
: v
Read_12/DisableCopyOnReadDisableCopyOnRead!read_12_disablecopyonread_count_1"/device:CPU:0*
_output_shapes
 Ы
Read_12/ReadVariableOpReadVariableOp!read_12_disablecopyonread_count_1^Read_12/DisableCopyOnRead"/device:CPU:0*
_output_shapes
: *
dtype0g
Identity_24IdentityRead_12/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes
: ]
Identity_25IdentityIdentity_24:output:0"/device:CPU:0*
T0*
_output_shapes
: t
Read_13/DisableCopyOnReadDisableCopyOnReadread_13_disablecopyonread_total"/device:CPU:0*
_output_shapes
 Щ
Read_13/ReadVariableOpReadVariableOpread_13_disablecopyonread_total^Read_13/DisableCopyOnRead"/device:CPU:0*
_output_shapes
: *
dtype0g
Identity_26IdentityRead_13/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes
: ]
Identity_27IdentityIdentity_26:output:0"/device:CPU:0*
T0*
_output_shapes
: t
Read_14/DisableCopyOnReadDisableCopyOnReadread_14_disablecopyonread_count"/device:CPU:0*
_output_shapes
 Щ
Read_14/ReadVariableOpReadVariableOpread_14_disablecopyonread_count^Read_14/DisableCopyOnRead"/device:CPU:0*
_output_shapes
: *
dtype0g
Identity_28IdentityRead_14/ReadVariableOp:value:0"/device:CPU:0*
T0*
_output_shapes
: ]
Identity_29IdentityIdentity_28:output:0"/device:CPU:0*
T0*
_output_shapes
: •
SaveV2/tensor_namesConst"/device:CPU:0*
_output_shapes
:*
dtype0*ќ
valueƒBЅB:layer_with_weights-0/embeddings/.ATTRIBUTES/VARIABLE_VALUEB6layer_with_weights-1/kernel/.ATTRIBUTES/VARIABLE_VALUEB4layer_with_weights-1/bias/.ATTRIBUTES/VARIABLE_VALUEB0optimizer/_iterations/.ATTRIBUTES/VARIABLE_VALUEB3optimizer/_learning_rate/.ATTRIBUTES/VARIABLE_VALUEB1optimizer/_variables/1/.ATTRIBUTES/VARIABLE_VALUEB1optimizer/_variables/2/.ATTRIBUTES/VARIABLE_VALUEB1optimizer/_variables/3/.ATTRIBUTES/VARIABLE_VALUEB1optimizer/_variables/4/.ATTRIBUTES/VARIABLE_VALUEB1optimizer/_variables/5/.ATTRIBUTES/VARIABLE_VALUEB1optimizer/_variables/6/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/0/total/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/0/count/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/1/total/.ATTRIBUTES/VARIABLE_VALUEB4keras_api/metrics/1/count/.ATTRIBUTES/VARIABLE_VALUEB_CHECKPOINTABLE_OBJECT_GRAPHН
SaveV2/shape_and_slicesConst"/device:CPU:0*
_output_shapes
:*
dtype0*3
value*B(B B B B B B B B B B B B B B B B ђ
SaveV2SaveV2ShardedFilename:filename:0SaveV2/tensor_names:output:0 SaveV2/shape_and_slices:output:0Identity_1:output:0Identity_3:output:0Identity_5:output:0Identity_7:output:0Identity_9:output:0Identity_11:output:0Identity_13:output:0Identity_15:output:0Identity_17:output:0Identity_19:output:0Identity_21:output:0Identity_23:output:0Identity_25:output:0Identity_27:output:0Identity_29:output:0savev2_const"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 *
dtypes
2	Р
&MergeV2Checkpoints/checkpoint_prefixesPackShardedFilename:filename:0^SaveV2"/device:CPU:0*
N*
T0*
_output_shapes
:≥
MergeV2CheckpointsMergeV2Checkpoints/MergeV2Checkpoints/checkpoint_prefixes:output:0file_prefix"/device:CPU:0*&
 _has_manual_control_dependencies(*
_output_shapes
 i
Identity_30Identityfile_prefix^MergeV2Checkpoints"/device:CPU:0*
T0*
_output_shapes
: U
Identity_31IdentityIdentity_30:output:0^NoOp*
T0*
_output_shapes
: ё
NoOpNoOp^MergeV2Checkpoints^Read/DisableCopyOnRead^Read/ReadVariableOp^Read_1/DisableCopyOnRead^Read_1/ReadVariableOp^Read_10/DisableCopyOnRead^Read_10/ReadVariableOp^Read_11/DisableCopyOnRead^Read_11/ReadVariableOp^Read_12/DisableCopyOnRead^Read_12/ReadVariableOp^Read_13/DisableCopyOnRead^Read_13/ReadVariableOp^Read_14/DisableCopyOnRead^Read_14/ReadVariableOp^Read_2/DisableCopyOnRead^Read_2/ReadVariableOp^Read_3/DisableCopyOnRead^Read_3/ReadVariableOp^Read_4/DisableCopyOnRead^Read_4/ReadVariableOp^Read_5/DisableCopyOnRead^Read_5/ReadVariableOp^Read_6/DisableCopyOnRead^Read_6/ReadVariableOp^Read_7/DisableCopyOnRead^Read_7/ReadVariableOp^Read_8/DisableCopyOnRead^Read_8/ReadVariableOp^Read_9/DisableCopyOnRead^Read_9/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "#
identity_31Identity_31:output:0*5
_input_shapes$
": : : : : : : : : : : : : : : : : 2(
MergeV2CheckpointsMergeV2Checkpoints20
Read/DisableCopyOnReadRead/DisableCopyOnRead2*
Read/ReadVariableOpRead/ReadVariableOp24
Read_1/DisableCopyOnReadRead_1/DisableCopyOnRead2.
Read_1/ReadVariableOpRead_1/ReadVariableOp26
Read_10/DisableCopyOnReadRead_10/DisableCopyOnRead20
Read_10/ReadVariableOpRead_10/ReadVariableOp26
Read_11/DisableCopyOnReadRead_11/DisableCopyOnRead20
Read_11/ReadVariableOpRead_11/ReadVariableOp26
Read_12/DisableCopyOnReadRead_12/DisableCopyOnRead20
Read_12/ReadVariableOpRead_12/ReadVariableOp26
Read_13/DisableCopyOnReadRead_13/DisableCopyOnRead20
Read_13/ReadVariableOpRead_13/ReadVariableOp26
Read_14/DisableCopyOnReadRead_14/DisableCopyOnRead20
Read_14/ReadVariableOpRead_14/ReadVariableOp24
Read_2/DisableCopyOnReadRead_2/DisableCopyOnRead2.
Read_2/ReadVariableOpRead_2/ReadVariableOp24
Read_3/DisableCopyOnReadRead_3/DisableCopyOnRead2.
Read_3/ReadVariableOpRead_3/ReadVariableOp24
Read_4/DisableCopyOnReadRead_4/DisableCopyOnRead2.
Read_4/ReadVariableOpRead_4/ReadVariableOp24
Read_5/DisableCopyOnReadRead_5/DisableCopyOnRead2.
Read_5/ReadVariableOpRead_5/ReadVariableOp24
Read_6/DisableCopyOnReadRead_6/DisableCopyOnRead2.
Read_6/ReadVariableOpRead_6/ReadVariableOp24
Read_7/DisableCopyOnReadRead_7/DisableCopyOnRead2.
Read_7/ReadVariableOpRead_7/ReadVariableOp24
Read_8/DisableCopyOnReadRead_8/DisableCopyOnRead2.
Read_8/ReadVariableOpRead_8/ReadVariableOp24
Read_9/DisableCopyOnReadRead_9/DisableCopyOnRead2.
Read_9/ReadVariableOpRead_9/ReadVariableOp:

_output_shapes
: :C ?

_output_shapes
: 
%
_user_specified_namefile_prefix
…
в
F__inference_sequential_layer_call_and_return_conditional_losses_102816

inputs4
!embedding_embedding_lookup_102801:	С6
$dense_matmul_readvariableop_resource:3
%dense_biasadd_readvariableop_resource:
identityИҐdense/BiasAdd/ReadVariableOpҐdense/MatMul/ReadVariableOpҐembedding/embedding_lookup_
embedding/CastCastinputs*

DstT0*

SrcT0*'
_output_shapes
:€€€€€€€€€г
embedding/embedding_lookupResourceGather!embedding_embedding_lookup_102801embedding/Cast:y:0*
Tindices0*4
_class*
(&loc:@embedding/embedding_lookup/102801*+
_output_shapes
:€€€€€€€€€*
dtype0ј
#embedding/embedding_lookup/IdentityIdentity#embedding/embedding_lookup:output:0*
T0*4
_class*
(&loc:@embedding/embedding_lookup/102801*+
_output_shapes
:€€€€€€€€€Х
%embedding/embedding_lookup/Identity_1Identity,embedding/embedding_lookup/Identity:output:0*
T0*+
_output_shapes
:€€€€€€€€€q
/global_average_pooling1d/Mean/reduction_indicesConst*
_output_shapes
: *
dtype0*
value	B :Ѕ
global_average_pooling1d/MeanMean.embedding/embedding_lookup/Identity_1:output:08global_average_pooling1d/Mean/reduction_indices:output:0*
T0*'
_output_shapes
:€€€€€€€€€А
dense/MatMul/ReadVariableOpReadVariableOp$dense_matmul_readvariableop_resource*
_output_shapes

:*
dtype0Х
dense/MatMulMatMul&global_average_pooling1d/Mean:output:0#dense/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€~
dense/BiasAdd/ReadVariableOpReadVariableOp%dense_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0И
dense/BiasAddBiasAdddense/MatMul:product:0$dense/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€b
dense/SigmoidSigmoiddense/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€`
IdentityIdentitydense/Sigmoid:y:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€†
NoOpNoOp^dense/BiasAdd/ReadVariableOp^dense/MatMul/ReadVariableOp^embedding/embedding_lookup*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*,
_input_shapes
:€€€€€€€€€: : : 2<
dense/BiasAdd/ReadVariableOpdense/BiasAdd/ReadVariableOp2:
dense/MatMul/ReadVariableOpdense/MatMul/ReadVariableOp28
embedding/embedding_lookupembedding/embedding_lookup:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
Ґ	
£
E__inference_embedding_layer_call_and_return_conditional_losses_102833

inputs*
embedding_lookup_102827:	С
identityИҐembedding_lookupU
CastCastinputs*

DstT0*

SrcT0*'
_output_shapes
:€€€€€€€€€ї
embedding_lookupResourceGatherembedding_lookup_102827Cast:y:0*
Tindices0**
_class 
loc:@embedding_lookup/102827*+
_output_shapes
:€€€€€€€€€*
dtype0Ґ
embedding_lookup/IdentityIdentityembedding_lookup:output:0*
T0**
_class 
loc:@embedding_lookup/102827*+
_output_shapes
:€€€€€€€€€Б
embedding_lookup/Identity_1Identity"embedding_lookup/Identity:output:0*
T0*+
_output_shapes
:€€€€€€€€€w
IdentityIdentity$embedding_lookup/Identity_1:output:0^NoOp*
T0*+
_output_shapes
:€€€€€€€€€Y
NoOpNoOp^embedding_lookup*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*(
_input_shapes
:€€€€€€€€€: 2$
embedding_lookupembedding_lookup:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
Ґ	
£
E__inference_embedding_layer_call_and_return_conditional_losses_102616

inputs*
embedding_lookup_102610:	С
identityИҐembedding_lookupU
CastCastinputs*

DstT0*

SrcT0*'
_output_shapes
:€€€€€€€€€ї
embedding_lookupResourceGatherembedding_lookup_102610Cast:y:0*
Tindices0**
_class 
loc:@embedding_lookup/102610*+
_output_shapes
:€€€€€€€€€*
dtype0Ґ
embedding_lookup/IdentityIdentityembedding_lookup:output:0*
T0**
_class 
loc:@embedding_lookup/102610*+
_output_shapes
:€€€€€€€€€Б
embedding_lookup/Identity_1Identity"embedding_lookup/Identity:output:0*
T0*+
_output_shapes
:€€€€€€€€€w
IdentityIdentity$embedding_lookup/Identity_1:output:0^NoOp*
T0*+
_output_shapes
:€€€€€€€€€Y
NoOpNoOp^embedding_lookup*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*(
_input_shapes
:€€€€€€€€€: 2$
embedding_lookupembedding_lookup:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
Ч

т
A__inference_dense_layer_call_and_return_conditional_losses_102632

inputs0
matmul_readvariableop_resource:-
biasadd_readvariableop_resource:
identityИҐBiasAdd/ReadVariableOpҐMatMul/ReadVariableOpt
MatMul/ReadVariableOpReadVariableOpmatmul_readvariableop_resource*
_output_shapes

:*
dtype0i
MatMulMatMulinputsMatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€r
BiasAdd/ReadVariableOpReadVariableOpbiasadd_readvariableop_resource*
_output_shapes
:*
dtype0v
BiasAddBiasAddMatMul:product:0BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€V
SigmoidSigmoidBiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€Z
IdentityIdentitySigmoid:y:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€w
NoOpNoOp^BiasAdd/ReadVariableOp^MatMul/ReadVariableOp*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime**
_input_shapes
:€€€€€€€€€: : 20
BiasAdd/ReadVariableOpBiasAdd/ReadVariableOp2.
MatMul/ReadVariableOpMatMul/ReadVariableOp:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
Љ
У
&__inference_dense_layer_call_fn_102853

inputs
unknown:
	unknown_0:
identityИҐStatefulPartitionedCall÷
StatefulPartitionedCallStatefulPartitionedCallinputsunknown	unknown_0*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*$
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *J
fERC
A__inference_dense_layer_call_and_return_conditional_losses_102632o
IdentityIdentity StatefulPartitionedCall:output:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€`
NoOpNoOp^StatefulPartitionedCall*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime**
_input_shapes
:€€€€€€€€€: : 22
StatefulPartitionedCallStatefulPartitionedCall:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
О
њ
+__inference_sequential_layer_call_fn_102701
embedding_input
unknown:	С
	unknown_0:
	unknown_1:
identityИҐStatefulPartitionedCallс
StatefulPartitionedCallStatefulPartitionedCallembedding_inputunknown	unknown_0	unknown_1*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*%
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *O
fJRH
F__inference_sequential_layer_call_and_return_conditional_losses_102692o
IdentityIdentity StatefulPartitionedCall:output:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€`
NoOpNoOp^StatefulPartitionedCall*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*,
_input_shapes
:€€€€€€€€€: : : 22
StatefulPartitionedCallStatefulPartitionedCall:X T
'
_output_shapes
:€€€€€€€€€
)
_user_specified_nameembedding_input
§

*__inference_embedding_layer_call_fn_102823

inputs
unknown:	С
identityИҐStatefulPartitionedCall—
StatefulPartitionedCallStatefulPartitionedCallinputsunknown*
Tin
2*
Tout
2*
_collective_manager_ids
 *+
_output_shapes
:€€€€€€€€€*#
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *N
fIRG
E__inference_embedding_layer_call_and_return_conditional_losses_102616s
IdentityIdentity StatefulPartitionedCall:output:0^NoOp*
T0*+
_output_shapes
:€€€€€€€€€`
NoOpNoOp^StatefulPartitionedCall*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*(
_input_shapes
:€€€€€€€€€: 22
StatefulPartitionedCallStatefulPartitionedCall:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
…
в
F__inference_sequential_layer_call_and_return_conditional_losses_102797

inputs4
!embedding_embedding_lookup_102782:	С6
$dense_matmul_readvariableop_resource:3
%dense_biasadd_readvariableop_resource:
identityИҐdense/BiasAdd/ReadVariableOpҐdense/MatMul/ReadVariableOpҐembedding/embedding_lookup_
embedding/CastCastinputs*

DstT0*

SrcT0*'
_output_shapes
:€€€€€€€€€г
embedding/embedding_lookupResourceGather!embedding_embedding_lookup_102782embedding/Cast:y:0*
Tindices0*4
_class*
(&loc:@embedding/embedding_lookup/102782*+
_output_shapes
:€€€€€€€€€*
dtype0ј
#embedding/embedding_lookup/IdentityIdentity#embedding/embedding_lookup:output:0*
T0*4
_class*
(&loc:@embedding/embedding_lookup/102782*+
_output_shapes
:€€€€€€€€€Х
%embedding/embedding_lookup/Identity_1Identity,embedding/embedding_lookup/Identity:output:0*
T0*+
_output_shapes
:€€€€€€€€€q
/global_average_pooling1d/Mean/reduction_indicesConst*
_output_shapes
: *
dtype0*
value	B :Ѕ
global_average_pooling1d/MeanMean.embedding/embedding_lookup/Identity_1:output:08global_average_pooling1d/Mean/reduction_indices:output:0*
T0*'
_output_shapes
:€€€€€€€€€А
dense/MatMul/ReadVariableOpReadVariableOp$dense_matmul_readvariableop_resource*
_output_shapes

:*
dtype0Х
dense/MatMulMatMul&global_average_pooling1d/Mean:output:0#dense/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€~
dense/BiasAdd/ReadVariableOpReadVariableOp%dense_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0И
dense/BiasAddBiasAdddense/MatMul:product:0$dense/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€b
dense/SigmoidSigmoiddense/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€`
IdentityIdentitydense/Sigmoid:y:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€†
NoOpNoOp^dense/BiasAdd/ReadVariableOp^dense/MatMul/ReadVariableOp^embedding/embedding_lookup*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*,
_input_shapes
:€€€€€€€€€: : : 2<
dense/BiasAdd/ReadVariableOpdense/BiasAdd/ReadVariableOp2:
dense/MatMul/ReadVariableOpdense/MatMul/ReadVariableOp28
embedding/embedding_lookupembedding/embedding_lookup:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
О
И
!__inference__wrapped_model_102589
embedding_input?
,sequential_embedding_embedding_lookup_102574:	СA
/sequential_dense_matmul_readvariableop_resource:>
0sequential_dense_biasadd_readvariableop_resource:
identityИҐ'sequential/dense/BiasAdd/ReadVariableOpҐ&sequential/dense/MatMul/ReadVariableOpҐ%sequential/embedding/embedding_lookups
sequential/embedding/CastCastembedding_input*

DstT0*

SrcT0*'
_output_shapes
:€€€€€€€€€П
%sequential/embedding/embedding_lookupResourceGather,sequential_embedding_embedding_lookup_102574sequential/embedding/Cast:y:0*
Tindices0*?
_class5
31loc:@sequential/embedding/embedding_lookup/102574*+
_output_shapes
:€€€€€€€€€*
dtype0б
.sequential/embedding/embedding_lookup/IdentityIdentity.sequential/embedding/embedding_lookup:output:0*
T0*?
_class5
31loc:@sequential/embedding/embedding_lookup/102574*+
_output_shapes
:€€€€€€€€€Ђ
0sequential/embedding/embedding_lookup/Identity_1Identity7sequential/embedding/embedding_lookup/Identity:output:0*
T0*+
_output_shapes
:€€€€€€€€€|
:sequential/global_average_pooling1d/Mean/reduction_indicesConst*
_output_shapes
: *
dtype0*
value	B :в
(sequential/global_average_pooling1d/MeanMean9sequential/embedding/embedding_lookup/Identity_1:output:0Csequential/global_average_pooling1d/Mean/reduction_indices:output:0*
T0*'
_output_shapes
:€€€€€€€€€Ц
&sequential/dense/MatMul/ReadVariableOpReadVariableOp/sequential_dense_matmul_readvariableop_resource*
_output_shapes

:*
dtype0ґ
sequential/dense/MatMulMatMul1sequential/global_average_pooling1d/Mean:output:0.sequential/dense/MatMul/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€Ф
'sequential/dense/BiasAdd/ReadVariableOpReadVariableOp0sequential_dense_biasadd_readvariableop_resource*
_output_shapes
:*
dtype0©
sequential/dense/BiasAddBiasAdd!sequential/dense/MatMul:product:0/sequential/dense/BiasAdd/ReadVariableOp:value:0*
T0*'
_output_shapes
:€€€€€€€€€x
sequential/dense/SigmoidSigmoid!sequential/dense/BiasAdd:output:0*
T0*'
_output_shapes
:€€€€€€€€€k
IdentityIdentitysequential/dense/Sigmoid:y:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€Ѕ
NoOpNoOp(^sequential/dense/BiasAdd/ReadVariableOp'^sequential/dense/MatMul/ReadVariableOp&^sequential/embedding/embedding_lookup*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*,
_input_shapes
:€€€€€€€€€: : : 2R
'sequential/dense/BiasAdd/ReadVariableOp'sequential/dense/BiasAdd/ReadVariableOp2P
&sequential/dense/MatMul/ReadVariableOp&sequential/dense/MatMul/ReadVariableOp2N
%sequential/embedding/embedding_lookup%sequential/embedding/embedding_lookup:X T
'
_output_shapes
:€€€€€€€€€
)
_user_specified_nameembedding_input
ў
К
F__inference_sequential_layer_call_and_return_conditional_losses_102692

inputs#
embedding_102682:	С
dense_102686:
dense_102688:
identityИҐdense/StatefulPartitionedCallҐ!embedding/StatefulPartitionedCallд
!embedding/StatefulPartitionedCallStatefulPartitionedCallinputsembedding_102682*
Tin
2*
Tout
2*
_collective_manager_ids
 *+
_output_shapes
:€€€€€€€€€*#
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *N
fIRG
E__inference_embedding_layer_call_and_return_conditional_losses_102616ь
(global_average_pooling1d/PartitionedCallPartitionedCall*embedding/StatefulPartitionedCall:output:0*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€* 
_read_only_resource_inputs
 *-
config_proto

CPU

GPU 2J 8В *]
fXRV
T__inference_global_average_pooling1d_layer_call_and_return_conditional_losses_102596П
dense/StatefulPartitionedCallStatefulPartitionedCall1global_average_pooling1d/PartitionedCall:output:0dense_102686dense_102688*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*$
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *J
fERC
A__inference_dense_layer_call_and_return_conditional_losses_102632u
IdentityIdentity&dense/StatefulPartitionedCall:output:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€К
NoOpNoOp^dense/StatefulPartitionedCall"^embedding/StatefulPartitionedCall*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*,
_input_shapes
:€€€€€€€€€: : : 2>
dense/StatefulPartitionedCalldense/StatefulPartitionedCall2F
!embedding/StatefulPartitionedCall!embedding/StatefulPartitionedCall:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs
М
p
T__inference_global_average_pooling1d_layer_call_and_return_conditional_losses_102596

inputs
identityX
Mean/reduction_indicesConst*
_output_shapes
: *
dtype0*
value	B :p
MeanMeaninputsMean/reduction_indices:output:0*
T0*0
_output_shapes
:€€€€€€€€€€€€€€€€€€^
IdentityIdentityMean:output:0*
T0*0
_output_shapes
:€€€€€€€€€€€€€€€€€€"
identityIdentity:output:0*(
_construction_contextkEagerRuntime*<
_input_shapes+
):'€€€€€€€€€€€€€€€€€€€€€€€€€€€:e a
=
_output_shapes+
):'€€€€€€€€€€€€€€€€€€€€€€€€€€€
 
_user_specified_nameinputs
у
ґ
+__inference_sequential_layer_call_fn_102778

inputs
unknown:	С
	unknown_0:
	unknown_1:
identityИҐStatefulPartitionedCallи
StatefulPartitionedCallStatefulPartitionedCallinputsunknown	unknown_0	unknown_1*
Tin
2*
Tout
2*
_collective_manager_ids
 *'
_output_shapes
:€€€€€€€€€*%
_read_only_resource_inputs
*-
config_proto

CPU

GPU 2J 8В *O
fJRH
F__inference_sequential_layer_call_and_return_conditional_losses_102692o
IdentityIdentity StatefulPartitionedCall:output:0^NoOp*
T0*'
_output_shapes
:€€€€€€€€€`
NoOpNoOp^StatefulPartitionedCall*"
_acd_function_control_output(*
_output_shapes
 "
identityIdentity:output:0*(
_construction_contextkEagerRuntime*,
_input_shapes
:€€€€€€€€€: : : 22
StatefulPartitionedCallStatefulPartitionedCall:O K
'
_output_shapes
:€€€€€€€€€
 
_user_specified_nameinputs"у
L
saver_filename:0StatefulPartitionedCall_1:0StatefulPartitionedCall_28"
saved_model_main_op

NoOp*>
__saved_model_init_op%#
__saved_model_init_op

NoOp*Є
serving_default§
K
embedding_input8
!serving_default_embedding_input:0€€€€€€€€€9
dense0
StatefulPartitionedCall:0€€€€€€€€€tensorflow/serving/predict:јm
Ѕ
layer_with_weights-0
layer-0
layer-1
layer_with_weights-1
layer-2
	variables
trainable_variables
regularization_losses
	keras_api
__call__
*	&call_and_return_all_conditional_losses

_default_save_signature
	optimizer

signatures"
_tf_keras_sequential
µ
	variables
trainable_variables
regularization_losses
	keras_api
__call__
*&call_and_return_all_conditional_losses

embeddings"
_tf_keras_layer
•
	variables
trainable_variables
regularization_losses
	keras_api
__call__
*&call_and_return_all_conditional_losses"
_tf_keras_layer
ї
	variables
trainable_variables
regularization_losses
	keras_api
__call__
*&call_and_return_all_conditional_losses

 kernel
!bias"
_tf_keras_layer
5
0
 1
!2"
trackable_list_wrapper
5
0
 1
!2"
trackable_list_wrapper
 "
trackable_list_wrapper
 
"non_trainable_variables

#layers
$metrics
%layer_regularization_losses
&layer_metrics
	variables
trainable_variables
regularization_losses
__call__

_default_save_signature
*	&call_and_return_all_conditional_losses
&	"call_and_return_conditional_losses"
_generic_user_object
„
'trace_0
(trace_1
)trace_2
*trace_32м
+__inference_sequential_layer_call_fn_102677
+__inference_sequential_layer_call_fn_102701
+__inference_sequential_layer_call_fn_102767
+__inference_sequential_layer_call_fn_102778µ
Ѓ≤™
FullArgSpec)
args!Ъ
jinputs

jtraining
jmask
varargs
 
varkw
 
defaultsҐ
p 

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 z'trace_0z(trace_1z)trace_2z*trace_3
√
+trace_0
,trace_1
-trace_2
.trace_32Ў
F__inference_sequential_layer_call_and_return_conditional_losses_102639
F__inference_sequential_layer_call_and_return_conditional_losses_102652
F__inference_sequential_layer_call_and_return_conditional_losses_102797
F__inference_sequential_layer_call_and_return_conditional_losses_102816µ
Ѓ≤™
FullArgSpec)
args!Ъ
jinputs

jtraining
jmask
varargs
 
varkw
 
defaultsҐ
p 

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 z+trace_0z,trace_1z-trace_2z.trace_3
‘B—
!__inference__wrapped_model_102589embedding_input"Ш
С≤Н
FullArgSpec
argsЪ 
varargsjargs
varkwjkwargs
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
Ь
/
_variables
0_iterations
1_learning_rate
2_index_dict
3
_momentums
4_velocities
5_update_step_xla"
experimentalOptimizer
,
6serving_default"
signature_map
'
0"
trackable_list_wrapper
'
0"
trackable_list_wrapper
 "
trackable_list_wrapper
≠
7non_trainable_variables

8layers
9metrics
:layer_regularization_losses
;layer_metrics
	variables
trainable_variables
regularization_losses
__call__
*&call_and_return_all_conditional_losses
&"call_and_return_conditional_losses"
_generic_user_object
д
<trace_02«
*__inference_embedding_layer_call_fn_102823Ш
С≤Н
FullArgSpec
argsЪ

jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 z<trace_0
€
=trace_02в
E__inference_embedding_layer_call_and_return_conditional_losses_102833Ш
С≤Н
FullArgSpec
argsЪ

jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 z=trace_0
':%	С2embedding/embeddings
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
≠
>non_trainable_variables

?layers
@metrics
Alayer_regularization_losses
Blayer_metrics
	variables
trainable_variables
regularization_losses
__call__
*&call_and_return_all_conditional_losses
&"call_and_return_conditional_losses"
_generic_user_object
А
Ctrace_02г
9__inference_global_average_pooling1d_layer_call_fn_102838•
Ю≤Ъ
FullArgSpec
argsЪ
jinputs
jmask
varargs
 
varkw
 
defaultsҐ

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 zCtrace_0
Ы
Dtrace_02ю
T__inference_global_average_pooling1d_layer_call_and_return_conditional_losses_102844•
Ю≤Ъ
FullArgSpec
argsЪ
jinputs
jmask
varargs
 
varkw
 
defaultsҐ

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 zDtrace_0
.
 0
!1"
trackable_list_wrapper
.
 0
!1"
trackable_list_wrapper
 "
trackable_list_wrapper
≠
Enon_trainable_variables

Flayers
Gmetrics
Hlayer_regularization_losses
Ilayer_metrics
	variables
trainable_variables
regularization_losses
__call__
*&call_and_return_all_conditional_losses
&"call_and_return_conditional_losses"
_generic_user_object
а
Jtrace_02√
&__inference_dense_layer_call_fn_102853Ш
С≤Н
FullArgSpec
argsЪ

jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 zJtrace_0
ы
Ktrace_02ё
A__inference_dense_layer_call_and_return_conditional_losses_102864Ш
С≤Н
FullArgSpec
argsЪ

jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 zKtrace_0
:2dense/kernel
:2
dense/bias
 "
trackable_list_wrapper
5
0
1
2"
trackable_list_wrapper
.
L0
M1"
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_dict_wrapper
ыBш
+__inference_sequential_layer_call_fn_102677embedding_input"µ
Ѓ≤™
FullArgSpec)
args!Ъ
jinputs

jtraining
jmask
varargs
 
varkw
 
defaultsҐ
p 

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
ыBш
+__inference_sequential_layer_call_fn_102701embedding_input"µ
Ѓ≤™
FullArgSpec)
args!Ъ
jinputs

jtraining
jmask
varargs
 
varkw
 
defaultsҐ
p 

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
тBп
+__inference_sequential_layer_call_fn_102767inputs"µ
Ѓ≤™
FullArgSpec)
args!Ъ
jinputs

jtraining
jmask
varargs
 
varkw
 
defaultsҐ
p 

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
тBп
+__inference_sequential_layer_call_fn_102778inputs"µ
Ѓ≤™
FullArgSpec)
args!Ъ
jinputs

jtraining
jmask
varargs
 
varkw
 
defaultsҐ
p 

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
ЦBУ
F__inference_sequential_layer_call_and_return_conditional_losses_102639embedding_input"µ
Ѓ≤™
FullArgSpec)
args!Ъ
jinputs

jtraining
jmask
varargs
 
varkw
 
defaultsҐ
p 

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
ЦBУ
F__inference_sequential_layer_call_and_return_conditional_losses_102652embedding_input"µ
Ѓ≤™
FullArgSpec)
args!Ъ
jinputs

jtraining
jmask
varargs
 
varkw
 
defaultsҐ
p 

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
НBК
F__inference_sequential_layer_call_and_return_conditional_losses_102797inputs"µ
Ѓ≤™
FullArgSpec)
args!Ъ
jinputs

jtraining
jmask
varargs
 
varkw
 
defaultsҐ
p 

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
НBК
F__inference_sequential_layer_call_and_return_conditional_losses_102816inputs"µ
Ѓ≤™
FullArgSpec)
args!Ъ
jinputs

jtraining
jmask
varargs
 
varkw
 
defaultsҐ
p 

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
Q
00
N1
O2
P3
Q4
R5
S6"
trackable_list_wrapper
:	 2	iteration
: 2learning_rate
 "
trackable_dict_wrapper
5
N0
P1
R2"
trackable_list_wrapper
5
O0
Q1
S2"
trackable_list_wrapper
µ2≤ѓ
¶≤Ґ
FullArgSpec*
args"Ъ

jgradient

jvariable
jkey
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 0
”B–
$__inference_signature_wrapper_102756embedding_input"Ф
Н≤Й
FullArgSpec
argsЪ 
varargs
 
varkwjkwargs
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_dict_wrapper
‘B—
*__inference_embedding_layer_call_fn_102823inputs"Ш
С≤Н
FullArgSpec
argsЪ

jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
пBм
E__inference_embedding_layer_call_and_return_conditional_losses_102833inputs"Ш
С≤Н
FullArgSpec
argsЪ

jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_dict_wrapper
рBн
9__inference_global_average_pooling1d_layer_call_fn_102838inputs"•
Ю≤Ъ
FullArgSpec
argsЪ
jinputs
jmask
varargs
 
varkw
 
defaultsҐ

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
ЛBИ
T__inference_global_average_pooling1d_layer_call_and_return_conditional_losses_102844inputs"•
Ю≤Ъ
FullArgSpec
argsЪ
jinputs
jmask
varargs
 
varkw
 
defaultsҐ

 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_list_wrapper
 "
trackable_dict_wrapper
–BЌ
&__inference_dense_layer_call_fn_102853inputs"Ш
С≤Н
FullArgSpec
argsЪ

jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
лBи
A__inference_dense_layer_call_and_return_conditional_losses_102864inputs"Ш
С≤Н
FullArgSpec
argsЪ

jinputs
varargs
 
varkw
 
defaults
 

kwonlyargsЪ 
kwonlydefaults
 
annotations™ *
 
N
T	variables
U	keras_api
	Vtotal
	Wcount"
_tf_keras_metric
^
X	variables
Y	keras_api
	Ztotal
	[count
\
_fn_kwargs"
_tf_keras_metric
,:*	С2Adam/m/embedding/embeddings
,:*	С2Adam/v/embedding/embeddings
#:!2Adam/m/dense/kernel
#:!2Adam/v/dense/kernel
:2Adam/m/dense/bias
:2Adam/v/dense/bias
.
V0
W1"
trackable_list_wrapper
-
T	variables"
_generic_user_object
:  (2total
:  (2count
.
Z0
[1"
trackable_list_wrapper
-
X	variables"
_generic_user_object
:  (2total
:  (2count
 "
trackable_dict_wrapperУ
!__inference__wrapped_model_102589n !8Ґ5
.Ґ+
)К&
embedding_input€€€€€€€€€
™ "-™*
(
denseК
dense€€€€€€€€€®
A__inference_dense_layer_call_and_return_conditional_losses_102864c !/Ґ,
%Ґ"
 К
inputs€€€€€€€€€
™ ",Ґ)
"К
tensor_0€€€€€€€€€
Ъ В
&__inference_dense_layer_call_fn_102853X !/Ґ,
%Ґ"
 К
inputs€€€€€€€€€
™ "!К
unknown€€€€€€€€€ѓ
E__inference_embedding_layer_call_and_return_conditional_losses_102833f/Ґ,
%Ґ"
 К
inputs€€€€€€€€€
™ "0Ґ-
&К#
tensor_0€€€€€€€€€
Ъ Й
*__inference_embedding_layer_call_fn_102823[/Ґ,
%Ґ"
 К
inputs€€€€€€€€€
™ "%К"
unknown€€€€€€€€€џ
T__inference_global_average_pooling1d_layer_call_and_return_conditional_losses_102844ВIҐF
?Ґ<
6К3
inputs'€€€€€€€€€€€€€€€€€€€€€€€€€€€

 
™ "5Ґ2
+К(
tensor_0€€€€€€€€€€€€€€€€€€
Ъ і
9__inference_global_average_pooling1d_layer_call_fn_102838wIҐF
?Ґ<
6К3
inputs'€€€€€€€€€€€€€€€€€€€€€€€€€€€

 
™ "*К'
unknown€€€€€€€€€€€€€€€€€€њ
F__inference_sequential_layer_call_and_return_conditional_losses_102639u !@Ґ=
6Ґ3
)К&
embedding_input€€€€€€€€€
p

 
™ ",Ґ)
"К
tensor_0€€€€€€€€€
Ъ њ
F__inference_sequential_layer_call_and_return_conditional_losses_102652u !@Ґ=
6Ґ3
)К&
embedding_input€€€€€€€€€
p 

 
™ ",Ґ)
"К
tensor_0€€€€€€€€€
Ъ ґ
F__inference_sequential_layer_call_and_return_conditional_losses_102797l !7Ґ4
-Ґ*
 К
inputs€€€€€€€€€
p

 
™ ",Ґ)
"К
tensor_0€€€€€€€€€
Ъ ґ
F__inference_sequential_layer_call_and_return_conditional_losses_102816l !7Ґ4
-Ґ*
 К
inputs€€€€€€€€€
p 

 
™ ",Ґ)
"К
tensor_0€€€€€€€€€
Ъ Щ
+__inference_sequential_layer_call_fn_102677j !@Ґ=
6Ґ3
)К&
embedding_input€€€€€€€€€
p

 
™ "!К
unknown€€€€€€€€€Щ
+__inference_sequential_layer_call_fn_102701j !@Ґ=
6Ґ3
)К&
embedding_input€€€€€€€€€
p 

 
™ "!К
unknown€€€€€€€€€Р
+__inference_sequential_layer_call_fn_102767a !7Ґ4
-Ґ*
 К
inputs€€€€€€€€€
p

 
™ "!К
unknown€€€€€€€€€Р
+__inference_sequential_layer_call_fn_102778a !7Ґ4
-Ґ*
 К
inputs€€€€€€€€€
p 

 
™ "!К
unknown€€€€€€€€€™
$__inference_signature_wrapper_102756Б !KҐH
Ґ 
A™>
<
embedding_input)К&
embedding_input€€€€€€€€€"-™*
(
denseК
dense€€€€€€€€€