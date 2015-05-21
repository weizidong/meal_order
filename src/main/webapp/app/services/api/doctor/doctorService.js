define([ 'app', 'Map', 'services/common/ajaxService' ], function(app, Map) {

	app.register.service('doctorService', [ 'ajaxService', '$rootScope', function(ajaxService, $rootScope) {

		var me = this;

		// 关联的医生
		var doctor;
		// key:doctorId
		var doctorMap = new Map();

		// 获取关联医生信息
		me.getDoctor = function(opts) {

			if (!(doctor === undefined) && !opts.refresh) {
				opts.success(doctor);
				return;
			}

			var oSuccess = opts.success;
			opts.success = function(data) {
				doctor = data[0];
				if (doctor) {
					doctorMap.put(doctor.id, doctor);
				}
				oSuccess(doctor);
			};

			ajaxService.postJson('/healthcenter/doctor/get_doctors', opts);
		};

		// 加载医生和患者信息
		me.getDoctorAndMember = function(success, error) {
			ajaxService.postJson('/healthcenter/doctor/get_doctorandmember', {
				success : success,
				error : error
			});
		};

		// 创建关联
		me.createRelation = function(qrcode, success, error) {
			ajaxService.postJson("/healthcenter/doctor/create_relation?qrcode=" + qrcode, {
				success : success,
				error : error
			});
		};

		// 删除关联
		me.delRelation = function(doctorId, success, error) {
			ajaxService.postJson('/healthcenter/doctor/delete_relation/' + doctorId, {
				success : success,
				error : error
			});
		};

		// 根据主键获取医生
		me.getById = function(doctorId, opts) {

			if (doctorMap.containsKey(doctorId) && !opts.refresh) {
				opts.success(doctorMap.get(doctorId));
				return;
			}
			var oSuccess = opts.success;
			opts.success = function(doctor) {
				if (doctor) {
					doctorMap.put(doctor.id, doctor);
				}
				oSuccess(doctor);
			};

			ajaxService.postJson('/healthcenter/doctor/get_by_id/' + doctorId, opts);
		};

		$rootScope.$on('onWsMessage', function(e, msg) {

			// 只处理 发送消息
			if (msg.cmd != 'unlink_doctor') {
				return;
			}

			var data = msg.data;
			var doctorId = data.doctorId;

			doctor = undefined;
		});

	} ]);
});